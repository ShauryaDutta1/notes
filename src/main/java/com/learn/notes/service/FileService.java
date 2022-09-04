package com.learn.notes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.FileRequest;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.ImageResponse;
import com.learn.notes.config.UploadResponse;
import com.learn.notes.model.File;
import com.learn.notes.model.Image;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.repository.FileRepository;
import com.learn.notes.repository.NotesRepository;
import com.learn.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ObjectMapper mapper;

    private RestTemplate restTemplate;

    @Value(value = "${s3.bucket.url}")
    private String BASE_URL;


    public GenericResponse<File> addFile(MultipartFile request,  String title, String description,
                                         String tag, String type, Notes notes){
        File file = new File();
        file.setTag(tag);
        file.setType(type);
        file.setTitle(title);
        file.setDescription(description);
        file.setTag(tag);
        file.setNotes(notes);
        file.setDeleted(false);
        file.setCreatedDate(LocalDateTime.now());
        file.setLastUpdatedDate(LocalDateTime.now());
        File saved = null;
        try {
            Notes savedNotes = notesRepository.findById(notes.getId()).get();
            if (savedNotes==null)
                return new GenericResponse<>("Error", "Notes Does Not Exist", saved);
            String userName = savedNotes.getUser().getFirstName()+" "+savedNotes.getUser().getLastName();
            file.setCreatedBy(userName);
            System.out.println("Name: " + userName);
            ImageResponse response = null;
            UploadResponse uploadResponse=null;
            try {
                 uploadResponse = storageService.uploadFile(request);
                 //response = fetchUrlAndSizeFromImageS3Bucket(request);
            }catch (Exception e){
                System.out.println("EX: "+ e.getMessage());
            }
            file.setUrl(uploadResponse.getUrl());
            file.setSize(uploadResponse.getSize());
            System.out.println("Response from template: " + response);
            System.out.println("Going to Save");
            saved = fileRepository.save(file);
            System.out.println("File Saved");
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        if (saved==null)
            return new GenericResponse<>("Error", "Something Went Wrong", saved);
        return new GenericResponse<>("Success", "File Saved Successfully", saved);

    }

    private ImageResponse fetchUrlAndSizeFromImageS3Bucket(MultipartFile file) {
        restTemplate = new RestTemplate();
        String url = BASE_URL + "/file/upload";
       HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
//        File file1 = new File(file);
//        form.add("file", );
        HttpEntity entity = new HttpEntity(file,headers);
        ImageResponse response=null;
        try {
             response = restTemplate.postForObject(url,entity,ImageResponse.class);
                    // url,entity, ImageResponse.class);
                    //.getBody();
            System.out.println("Image saved in Bucket");
        }catch (Exception e){
            System.out.println("Exception Occured: " + e.getMessage());
        }
        return response;
    }

    public List<File> getAllFiles() {
        List<File> files = fileRepository.findAll();
        System.out.println("Notes: " + files);
        return files;
    }

    public List<File> getAllFilesByNotes(Long noteId) {
        List<File> files = fileRepository.findAllByNoteId(noteId);
        System.out.println("Notes: " + files);
        return files;
    }
}
