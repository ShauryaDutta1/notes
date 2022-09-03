package com.learn.notes.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericResponse<Object> {

    private String status;
    private String message;
    private Object data;
}
