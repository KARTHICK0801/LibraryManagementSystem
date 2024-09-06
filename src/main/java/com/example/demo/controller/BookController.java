package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/get")
    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        logger.info("Fetching book with ID: {}", id);
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveBook(@RequestBody Book book) {
        logger.info("Saving book: {}", book);
        bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Book book) {
        logger.info("Updating book with ID: {}", id);
        book.setId(id);
        bookService.updateBook(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        logger.info("Deleting book with ID: {}", id);
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("query") String searchText) {
        logger.info("Searching books with query: {}", searchText);
        return bookService.searchBooks(searchText);
    }
}
