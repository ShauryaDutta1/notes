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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/")
    private GenericResponse<File> addFile(@RequestBody FileRequest request){
        GenericResponse<File> savedFile = fileService.addFile(request);
        return savedFile;
    }

    @GetMapping(value = "/")
    private GenericResponseMulti<List<User>> getAllFiles(){
        List<File> files = fileService.getAllFiles();
        GenericResponseMulti response = new GenericResponseMulti("Success","Files Fetched", files);
        System.out.println("Generic Response: " + response);
        return response;
    }
}
