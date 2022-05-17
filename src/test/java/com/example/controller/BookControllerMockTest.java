package com.example.controller;

import java.util.Objects;
import java.util.Optional;

import com.example.domain.Book;
import com.example.exception.BookNotFoundException;
import com.example.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {BookController.class})
public class BookControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void bookShouldReturnFromRepository() throws Exception {
        String bookId = "1";

        Book book = new Book();
        book.setId("1");
        book.setTitle("Spring");
        when(bookService.findById(bookId)).thenReturn(Optional.of(book));
        this.mockMvc.perform(get("/api/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void bookShouldThrowException() throws Exception {
        String bookId = "2";
        when(bookService.findById(bookId)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/api/books/2"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookNotFoundException))
                .andExpect(result -> assertEquals("Book with id 2 is not found.",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
