package com.learn.notes.config;


import com.learn.notes.model.File;
import com.learn.notes.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotesResponse {

    private String title;
    private String description;
    private String tags;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private String accessType;
    //private User user;
    //private Set<File> files;

}
