package com.example.demo.service;

import com.example.demo.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void saveBook(Book book);
    void updateBook(Book book);
    void deleteBookById(int id);
    List<Book> searchBooks(String searchText);
}