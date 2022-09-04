package com.learn.notes.config;

import com.learn.notes.model.Notes;
import com.learn.notes.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileRequest {

    private String title;
    private String description;
    private String tag;
    private String accessType;
    private String type;
    private Notes notes;
    private MultipartFile file;

}
