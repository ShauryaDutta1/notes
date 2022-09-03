package com.learn.notes.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericResponseMulti<Object> {

    private String status;
    private String message;
    private List<Object> data;
}
