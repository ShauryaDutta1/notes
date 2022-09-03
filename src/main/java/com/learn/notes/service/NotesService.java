package com.learn.notes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.model.Notes;
import com.learn.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    private ObjectMapper mapper;

    public GenericResponse<Notes> addNote(NotesRequest request) {
        mapper = new ObjectMapper();
        Notes notes = mapper.convertValue(request, Notes.class);
        notes.setDeleted(false);
        notes.setCreatedDate(LocalDateTime.now());
        notes.setLastUpdatedDate(LocalDateTime.now());
        Notes saved = null;
        try {
            saved = notesRepository.save(notes);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        if (saved==null)
            return new GenericResponse<>("Error", "Something Went Wrong", saved);
        return new GenericResponse<>("Success", "Note Saved Successfully", saved);
    }
}
