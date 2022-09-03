package com.learn.notes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.FileRequest;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.model.File;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.repository.FileRepository;
import com.learn.notes.repository.NotesRepository;
import com.learn.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private ObjectMapper mapper;


    public GenericResponse<File> addFile(@RequestBody FileRequest request){
        mapper = new ObjectMapper();
        File file = mapper.convertValue(request, File.class);
        file.setDeleted(false);
        file.setCreatedDate(LocalDateTime.now());
        file.setLastUpdatedDate(LocalDateTime.now());
        File saved = null;
        try {
            Notes notes = notesRepository.findById(request.getNotes().getId()).get();
            if (notes==null)
                return new GenericResponse<>("Error", "Notes Does Not Exist", saved);
            String userName = notes.getUser().getFirstName()+" "+notes.getUser().getLastName();
            file.setCreatedBy(userName);
            System.out.println("Name: " + userName);
           saved = fileRepository.save(file);
            System.out.println("File Saved");
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        if (saved==null)
            return new GenericResponse<>("Error", "Something Went Wrong", saved);
        return new GenericResponse<>("Success", "File Saved Successfully", saved);

    }

    public List<File> getAllFiles() {
        List<File> files = fileRepository.findAll();
        System.out.println("Notes: " + files);
        return files;
    }
}
