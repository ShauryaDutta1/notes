package com.learn.notes.api;

import com.learn.notes.config.FileRequest;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.GenericResponseMulti;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.config.NotesResponse;
import com.learn.notes.model.File;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.service.FileService;
import com.learn.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/")
    private GenericResponse<File> addFile(@RequestParam String title,
                                          @RequestParam String description,
                                          @RequestParam String tag,
                                          @RequestParam String type,
                                          @RequestParam Notes notes,
                                          @RequestBody MultipartFile file){
        GenericResponse<File> savedFile = fileService.addFile(file, title, description, tag, type, notes);
        return savedFile;
    }

    @GetMapping(value = "/all")
    private GenericResponseMulti<List<User>> getAllFiles(){
        List<File> files = fileService.getAllFiles();
        GenericResponseMulti response = new GenericResponseMulti("Success","Files Fetched", files);
        System.out.println("Generic Response: " + response);
        return response;
    }

    @GetMapping(value = "/")
    private GenericResponseMulti<List<User>> getFilesByNotes(@RequestParam("noteId") Long noteId){
        List<File> files = fileService.getAllFilesByNotes(noteId);
        GenericResponseMulti response = new GenericResponseMulti("Success","Files Fetched", files);
        System.out.println("Generic Response: " + response);
        return response;
    }
}
