package com.learn.notes.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.model.Notes;
import com.learn.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;


    @PostMapping("/")
    private GenericResponse<Notes> addNote(@RequestBody NotesRequest request){
        GenericResponse<Notes> savedNotes = notesService.addNote(request);
        return savedNotes;
    }

}
