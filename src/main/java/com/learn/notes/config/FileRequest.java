package com.learn.notes.config;

import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileRequest {

    private String title;
    private String description;
    private String tag;
    private String accessType;
    private String url;
    private String size;
    private Notes notes;

}
