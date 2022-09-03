package com.learn.notes.api;

import com.learn.notes.service.FileService;
import com.learn.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;


}
