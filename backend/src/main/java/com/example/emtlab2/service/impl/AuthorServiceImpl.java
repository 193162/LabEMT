package com.example.emtlab2.service.impl;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Country;
import com.example.emtlab2.model.dto.AuthorDto;
import com.example.emtlab2.model.exceptions.AuthorNotFoundException;
import com.example.emtlab2.repository.AuthorRepository;
import com.example.emtlab2.repository.CountryRepository;
import com.example.emtlab2.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findByName(String name) {
        return this.authorRepository.findAllByName(name);
    }

    @Override
    public List<Author> findByNameAndSurname(String name, String surname) {
        return this.authorRepository.findAllByNameAndSurname(name, surname);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String surname, Country country) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(authorDto.getCountry());
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    @Transactional
    public Optional<Author> save(String name, String surname, Country country) {
        Author author = new Author(name, surname, country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    public Optional<Author> save(AuthorDto authorDto) {
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), authorDto.getCountry());
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {

    }
}
