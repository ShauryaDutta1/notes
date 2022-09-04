package com.learn.notes.repository;

import com.learn.notes.model.File;
import com.learn.notes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {


    @Query(value = "select * from tbl_file", nativeQuery = true)
    List<File> findAllByNoteId(Long noteId);
}
