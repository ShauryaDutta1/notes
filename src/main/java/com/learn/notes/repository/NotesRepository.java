package com.learn.notes.repository;

import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {


}
