package com.learn.notes.repository;

import com.learn.notes.config.NotesResponse;
import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {


    @Query(value = "select new com.learn.notes.config.NotesResponse(n.title, n.description, n.tags, n.createdDate, " +
            " n.lastUpdatedDate, n.accessType) " +
            " from Notes n order by 1 desc")
    List<NotesResponse> findAllNotes();
}
