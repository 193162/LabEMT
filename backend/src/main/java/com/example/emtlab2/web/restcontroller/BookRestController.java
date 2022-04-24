package com.example.emtlab2.web.restcontroller;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.dto.BookDto;
import com.example.emtlab2.model.enumerations.Category;
import com.example.emtlab2.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search-book")
    private ResponseEntity<Book> findByName(@RequestParam String name) {
        return this.bookService.findByName(name).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search-author")
    public List<Book> findByAuthor(@RequestParam Author author) {
        return this.bookService.findByAuthor(author);
    }

    @GetMapping("/search-category")
    public List<Book> findAllByCategory(@RequestParam Category category) {
        return this.bookService.findAllByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/increment-down/{id}")
    public ResponseEntity<Book> incrementDown(@PathVariable Long id){
        return this.bookService.incrementCopiesDown(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/increment-up/{id}")
    public ResponseEntity<Book> incrementUp(@PathVariable Long id){
        return this.bookService.incrementCopiesUp(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
