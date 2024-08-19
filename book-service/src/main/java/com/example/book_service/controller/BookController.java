package com.example.book_service.controller;

import com.example.book_service.converter.BookDtoConverter;
import com.example.book_service.dto.BookDto;
import com.example.book_service.entity.Book;
import com.example.book_service.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookDtoConverter bookDtoConverter;
    public BookController(BookService bookService, BookDtoConverter bookDtoConverter) {
        this.bookService = bookService;
        this.bookDtoConverter = bookDtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }
    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookDtoConverter.convertToDto(bookService.getBooksById(id));
    }
   @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.addBooks(bookDtoConverter.convertToEntity(dto)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id,bookDtoConverter.convertToEntity(dto)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
