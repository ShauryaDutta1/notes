package com.learn.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {

	public static void main(String[] args) {
		System.out.print("hello");
		SpringApplication.run(NotesApplication.class, args);
	}

}
