package com.learn.notes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.config.NotesResponse;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.repository.NotesRepository;
import com.learn.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UserRepository userRepository;
    private ObjectMapper mapper;

    public GenericResponse<Notes> addNote(NotesRequest request) {
        mapper = new ObjectMapper();
        Notes notes = mapper.convertValue(request, Notes.class);
        notes.setDeleted(false);
        notes.setCreatedDate(LocalDateTime.now());
        notes.setLastUpdatedDate(LocalDateTime.now());
        Notes saved = null;
        try {
            User user = userRepository.findUserById(request.getUser().getInstituteId());
            if (user==null)
                return new GenericResponse<>("Error", "User Does Not Exist", saved);
            saved = notesRepository.save(notes);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        if (saved==null)
            return new GenericResponse<>("Error", "Something Went Wrong", saved);
        return new GenericResponse<>("Success", "Note Saved Successfully", saved);
    }

    public List<NotesResponse> getAllNotes() {
        List<NotesResponse> allNotes = null;
        try {
             allNotes = notesRepository.findAllNotes();
        }catch (Exception e){
            System.out.println("Exception Occured: " + e.getMessage());
        }
    return allNotes;
    }
}
