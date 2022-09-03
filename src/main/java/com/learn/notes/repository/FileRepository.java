package com.learn.notes.repository;

import com.learn.notes.model.File;
import com.learn.notes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {


}
