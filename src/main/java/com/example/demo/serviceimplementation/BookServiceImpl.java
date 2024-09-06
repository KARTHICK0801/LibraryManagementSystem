package com.example.demo.serviceimplementation;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

//    @Override
//    public void saveBook(Book book) {
//        bookRepository.save(book);
//    }

    @Override
    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }
    @Override
    public void saveBook(Book book) {
        bookRepository.saveBook(book);
    }

    @Override
    public List<Book> searchBooks(String searchText) {
        return bookRepository.searchByTitleOrAuthorOrIsbn(searchText);
    }
}
