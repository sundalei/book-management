package com.example.service;

import com.example.domain.Book;
import com.example.exception.BookNotFoundException;
import com.example.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book findById(String id) {
        return this.repository.findById(id)
         .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " is not found."));
    }

    public Iterable<Book> findByTitle(String title) {
        return this.repository.findBookByTitle(title);
    }

    public Iterable<Book> allBooks() {
        return this.repository.findAll();
    }

    public Page<Book> allBooks(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Book postBook(Book book) {
        return this.repository.save(book);
    }

    public Iterable<Book> findByAuthor(String author) {
        return this.repository.findBookByAuthor(author);
    }

    public String deleteBook(String id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return id;
        } 
        return null;
    }
}
