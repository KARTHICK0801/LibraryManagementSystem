package com.example.demo.serviceimplementation;

import com.example.demo.entity.Borrow;
import com.example.demo.repo.BorrowRepository;
import com.example.demo.service.BorrowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow getBorrowById(int id) {
        return borrowRepository.findById(id);
    }

    @Override
    public void borrowBook(Borrow borrow) {
        borrowRepository.save(borrow);
    }

    @Override
    public void returnBook(int id) {
        borrowRepository.deleteById(id);
    }

    // Implement the new methods

    @Override
    public int getAvailableCopies(int bookId) {
        return borrowRepository.getAvailableCopies(bookId);
    }

    @Override
    public int getBorrowedBooksCount(int memberId) {
        return borrowRepository.getBorrowedBooksCount(memberId);
    }

    @Override
    public void decrementAvailableCopies(int bookId) {
        borrowRepository.decrementAvailableCopies(bookId);
    }

    @Override
    public void incrementAvailableCopies(int bookId) {
        borrowRepository.incrementAvailableCopies(bookId);
    }
}
