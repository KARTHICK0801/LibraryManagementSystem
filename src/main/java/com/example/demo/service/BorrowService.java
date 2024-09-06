package com.example.demo.service;

import com.example.demo.entity.Borrow;

import java.util.List;

public interface BorrowService {
    List<Borrow> getAllBorrows();
    Borrow getBorrowById(int id);
    void borrowBook(Borrow borrow);
    void returnBook(int id);
    int getAvailableCopies(int bookId);
    int getBorrowedBooksCount(int memberId);
    void decrementAvailableCopies(int bookId);
    void incrementAvailableCopies(int bookId);
}
