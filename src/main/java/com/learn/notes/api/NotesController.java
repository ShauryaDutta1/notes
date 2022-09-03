package com.learn.notes.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.GenericResponseMulti;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.config.NotesResponse;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/")
    private GenericResponseMulti<List<NotesResponse>> getAllNotes(){
        List<NotesResponse> notes = notesService.getAllNotes();
        if (notes==null || notes.size()==0)
            return new GenericResponseMulti("Error","Something Went Wrong", null);
        GenericResponseMulti response = new GenericResponseMulti("Success","Users Fetched", notes);
        System.out.println("Generic Response: " + response);
        return response;
    }

}
