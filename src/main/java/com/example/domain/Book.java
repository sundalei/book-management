package com.example.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("books")
public class Book {

    @Id
    private String id;

    private String isbn;

    private String title;

    private List<String> author;

    private int publishYear;

    private List<String> tags;
}
