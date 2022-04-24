package com.example.emtlab2.service.impl;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.dto.BookDto;
import com.example.emtlab2.model.enumerations.Category;
import com.example.emtlab2.model.exceptions.BookNotFoundException;
import com.example.emtlab2.repository.BookRepository;
import com.example.emtlab2.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findBookByName(name);
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return this.bookRepository.findAllByAuthor(author);
    }

    @Override
    public List<Book> findAllByCategory(Category category) {
        return this.bookRepository.findAllByCategory(category);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Author author) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(bookDto.getAuthor());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Category category, Author author, Integer availableCopies) {
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> incrementCopiesUp(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer availableCopies = book.getAvailableCopies();
        book.setAvailableCopies(++availableCopies);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> incrementCopiesDown(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer availableCopies = book.getAvailableCopies();
        book.setAvailableCopies(--availableCopies);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {

    }
}
