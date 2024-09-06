package com.example.demo.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public Book findById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
    }

    public void saveBook(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, published_date, available_copies) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublishedDate(), book.getAvailableCopies());
    }

    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, published_date = ?, available_copies = ? WHERE id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublishedDate(), book.getAvailableCopies(), book.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Book> searchByTitleOrAuthorOrIsbn(String searchText) {
        String sql = "SELECT * FROM books WHERE title ILIKE ? OR author ILIKE ? OR isbn ILIKE ?";
        String query = "%" + searchText + "%";
        return jdbcTemplate.query(sql, new BookRowMapper(), query, query, query);
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setPublishedDate(rs.getDate("published_date").toLocalDate());
            book.setAvailableCopies(rs.getInt("available_copies"));
            return book;
        }
    }
}
