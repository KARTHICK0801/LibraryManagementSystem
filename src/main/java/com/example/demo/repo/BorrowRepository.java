package com.example.demo.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Borrow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BorrowRepository {

    private final JdbcTemplate jdbcTemplate;

    public BorrowRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Borrow> findAll() {
        String sql = "SELECT * FROM borrow";
        return jdbcTemplate.query(sql, new BorrowRowMapper());
    }

    public Borrow findById(int id) {
        String sql = "SELECT * FROM borrows WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BorrowRowMapper(), id);
    }

    public void save(Borrow borrow) {
        String sql = "INSERT INTO borrows (member_id, book_id, borrowed_date, due_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, borrow.getMemberId(), borrow.getBookId(), borrow.getBorrowedDate(), borrow.getDueDate());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM borrows WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateBorrow(Borrow borrow) {
        String sql = "UPDATE borrows SET borrowed_date = ?, due_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, borrow.getBorrowedDate(), borrow.getDueDate(), borrow.getId());
    }

    public void decrementAvailableCopies(int bookId) {
        String sql = "UPDATE books SET available_copies = available_copies - 1 WHERE id = ? AND available_copies > 0";
        jdbcTemplate.update(sql, bookId);
    }

    public void incrementAvailableCopies(int bookId) {
        String sql = "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?";
        jdbcTemplate.update(sql, bookId);
    }
    public int getAvailableCopies(int bookId) {
        String sql = "SELECT available_copies FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    public int getBorrowedBooksCount(int memberId) {
        String sql = "SELECT COUNT(*) FROM borrows WHERE member_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, memberId);
    }


    private static class BorrowRowMapper implements RowMapper<Borrow> {
        @Override
        public Borrow mapRow(ResultSet rs, int rowNum) throws SQLException {
            Borrow borrow = new Borrow();
            borrow.setId(rs.getInt("id"));
            borrow.setMemberId(rs.getInt("member_id"));
            borrow.setBookId(rs.getInt("book_id"));
            borrow.setBorrowedDate(rs.getDate("borrowed_date").toLocalDate());
            borrow.setDueDate(rs.getDate("due_date").toLocalDate());
            return borrow;
        }
    }
}
