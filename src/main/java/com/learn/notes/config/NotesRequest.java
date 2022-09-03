package com.learn.notes.config;

import com.learn.notes.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotesRequest {

    private String title;
    private String description;
    private String tags;
    private String accessType;
    private User user;

}
