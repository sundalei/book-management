package com.example.controller;

import java.util.List;
import java.util.Map;

import com.example.domain.Book;
import com.example.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable String id) {
        return bookService.findById(id);
    }

    @GetMapping(value = "/title")
    public Iterable<Book> findByTitle(@RequestHeader String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping(value = "/author")
    public Iterable<Book> findByAuthor(@RequestHeader String author) {
        return bookService.findByAuthor(author);
    }

    @GetMapping
    public Iterable<Book> findBooks() {
        return bookService.allBooks();
    }

    @GetMapping("/filter")
    public Page<Book> filterBook(Pageable pageable) {
        return bookService.allBooks(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@RequestBody final Book book) {
        return this.bookService.postBook(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook(@PathVariable final String id) {
        return this.bookService.deleteBook(id);
    }
}
