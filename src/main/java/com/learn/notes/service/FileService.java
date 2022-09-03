package com.learn.notes.service;

import com.learn.notes.repository.FileRepository;
import com.learn.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

}
