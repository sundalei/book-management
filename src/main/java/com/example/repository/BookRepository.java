package com.example.repository;

import com.example.domain.Book;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {

}
