package com.example.repository;

import java.util.Optional;

import com.example.domain.Book;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {

    Optional<Book> findBookByIsbn(String isbn);

    @Query("{'title' : { $regex: ?0}}")
    Iterable<Book> findBookByTitle(String title);

    @Query("{'author': ?0}")
    Iterable<Book> findBookByAuthor(String author);

}
