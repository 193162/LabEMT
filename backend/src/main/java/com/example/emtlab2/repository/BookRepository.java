package com.example.emtlab2.repository;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByName(String name);
    List<Book> findAllByAuthor(Author author);
    List<Book> findAllByCategory(Category category);
}
