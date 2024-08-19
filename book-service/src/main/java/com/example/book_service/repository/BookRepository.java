package com.example.book_service.repository;

import com.example.book_service.entity.Book;
import com.example.book_service.exception.BookNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
