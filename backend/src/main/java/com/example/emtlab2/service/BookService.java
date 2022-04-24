package com.example.emtlab2.service;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.dto.BookDto;
import com.example.emtlab2.model.enumerations.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(Author author);
    List<Book> findAllByCategory(Category category);
    Optional<Book> edit(Long id, String name, Category category, Author author);
    Optional<Book> edit(Long id, BookDto bookDto);
    Optional<Book> save(String name, Category category, Author author, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> incrementCopiesUp(Long id);
    Optional<Book> incrementCopiesDown(Long id);
    void deleteById(Long id);
    void refreshMaterializedView();
}
