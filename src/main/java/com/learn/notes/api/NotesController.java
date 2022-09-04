package com.learn.notes.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.GenericResponseMulti;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.config.NotesResponse;
import com.learn.notes.model.File;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*@GetMapping(value = "/all")
    private GenericResponseMulti<List<NotesResponse>> getAllNotes(){
        List<NotesResponse> notes = notesService.getAllNotes();
        if (notes==null || notes.size()==0)
            return new GenericResponseMulti("Error","Something Went Wrong", null);
        GenericResponseMulti response = new GenericResponseMulti("Success","Notes Fetched", notes);
        System.out.println("Generic Response: " + response);
        return response;
    }
     */

    @GetMapping(value = "/all")
    private GenericResponseMulti<List<Notes>> getAllNotess(){
        List<Notes> users = notesService.getAllNotess();
        GenericResponseMulti response = new GenericResponseMulti("Success","Notes Fetched", users);
        System.out.println("Generic Response: " + response);
        return response;
    }

    @GetMapping(value = "/")
    private GenericResponse<List<File>> getAllNotesFilesByNoteId(@RequestParam("noteId") Long noteId){
        Notes notes = notesService.getAllNotesById(noteId);
        if (notes == null)
            return new GenericResponse("Error", "No Data Found", notes);
        GenericResponse response = new GenericResponse("Success","Files Fetched", notes.getFiles());
        System.out.println("Generic Response: " + response);
        return response;
    }

    @DeleteMapping(value = "/{id}")
    private GenericResponse<Boolean> deleteNotes(@PathVariable("id") Long id){
        return notesService.deleteNotes(id);
    }

}
