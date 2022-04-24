package com.example.emtlab2.web.restcontroller;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.dto.AuthorDto;
import com.example.emtlab2.model.dto.BookDto;
import com.example.emtlab2.model.enumerations.Category;
import com.example.emtlab2.service.AuthorService;
import com.example.emtlab2.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search-author-by-name")
    private List<Author> findByName(@RequestParam String name) {
        return this.authorService.findByName(name);
    }

    @GetMapping("/search-author-by-name-and-surname")
    private List<Author> findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return this.authorService.findByNameAndSurname(name, surname);
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) {
        return this.authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.edit(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
        if(this.authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
