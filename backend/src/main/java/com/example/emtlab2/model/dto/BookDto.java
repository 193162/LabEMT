package com.example.emtlab2.model.dto;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.enumerations.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Author author;

    private Integer availableCopies;

    public BookDto() {
    }


    public BookDto(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
