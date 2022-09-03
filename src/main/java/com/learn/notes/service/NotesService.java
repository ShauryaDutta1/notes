package com.learn.notes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.NotesRequest;
import com.learn.notes.config.NotesResponse;
import com.learn.notes.config.NotesUserResponse;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import com.learn.notes.repository.NotesRepository;
import com.learn.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        List<NotesUserResponse> allUserNotes = null;
        //List<NotesFileResponse> allFileNotes = null;
        List<Notes> notes = new ArrayList<>();
        try {
             allNotes = notesRepository.findAllNotes();
             for (NotesResponse notesResponse : allNotes){
                 Notes notes1 = new Notes();
                 notes1 = mapper.convertValue(notesResponse, Notes.class);
                 //notes1.setUser();
             }
             //allUserNotes = userRepository.findNameById();
        }catch (Exception e){
            System.out.println("Exception Occured: " + e.getMessage());
        }
    return allNotes;
    }

    public List<Notes> getAllNotess() {
        List<Notes> users = notesRepository.findAll();
        System.out.println("Notes: " + users);
        return users;
    }

    public GenericResponse<Boolean> deleteNotes(Long id) {
        try {
            notesRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return new GenericResponse<>("Error", "Some Error Occured", false);
        }
        return new GenericResponse<>("Success", "Notes Deleted", true);
    }
}
