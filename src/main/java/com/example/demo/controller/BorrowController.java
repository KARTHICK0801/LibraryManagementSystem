package com.example.demo.controller;

import com.example.demo.entity.Borrow;
import com.example.demo.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/get")
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    // Lend a book to a member
    @PostMapping("/lend")
    public ResponseEntity<String> borrowBook(@RequestBody Borrow borrow) {
        int availableCopies = borrowService.getAvailableCopies(borrow.getBookId());
        int borrowedBooksCount = borrowService.getBorrowedBooksCount(borrow.getMemberId());

        // Check if the book is available
        if (availableCopies <= 0) {
            return ResponseEntity.badRequest().body("Book is not available for borrowing.");
        }

        // Check if the member has exceeded the borrowing limit (e.g., limit is 5 books)
        if (borrowedBooksCount >= 5) {
            return ResponseEntity.badRequest().body("Member has reached the borrowing limit.");
        }

        // Set borrow date and due date
        borrow.setBorrowedDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(14));

        // Borrow the book	
        borrowService.borrowBook(borrow);

        // Decrease available copies
        borrowService.decrementAvailableCopies(borrow.getBookId());

        return ResponseEntity.ok("Book borrowed successfully!");
    }

    // Return a book
    @PutMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable int id) {
        Borrow borrow = borrowService.getBorrowById(id);
        if (borrow == null) {
            return ResponseEntity.badRequest().body("Borrow record not found.");
        }

        // Return the book and increment available copies
        borrowService.returnBook(id);
        borrowService.incrementAvailableCopies(borrow.getBookId());

        return ResponseEntity.ok("Book returned successfully!");
    }
}
