package com.example.emtlab2.service;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Country;
import com.example.emtlab2.model.dto.AuthorDto;
import com.example.emtlab2.service.impl.AuthorServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> findByName(String name);
    List<Author> findByNameAndSurname(String name, String surname);
    List<Author> findAll();
    Optional<Author> edit(Long id, String name, String surname, Country country);
    Optional<Author> edit(Long id, AuthorDto authorDto);
    Optional<Author> save(String name, String surname, Country country);
    Optional<Author> save(AuthorDto authorDto);
    void deleteById(Long id);
    void refreshMaterializedView();
}
