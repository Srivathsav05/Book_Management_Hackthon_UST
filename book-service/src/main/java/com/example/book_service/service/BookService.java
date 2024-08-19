package com.example.book_service.service;

import com.example.book_service.entity.Book;
import com.example.book_service.exception.BookNotFoundException;
import com.example.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBooks(Book book) {
        this.bookRepository.save(book);
        return book;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBooksById(long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            return existingBook.get();
        } else {
            throw new BookNotFoundException("Book is Not Found This Id:" + id);
        }
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(long id, Book book) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            existingBook.get().setTitle(book.getTitle());
            existingBook.get().setAuthor(book.getAuthor());
            existingBook.get().setPrice(book.getPrice());
            existingBook.get().setStock(book.getStock());
            return bookRepository.save(existingBook.get());
        }
        throw new BookNotFoundException("Book is not Found id" + id);

    }
}