package com.example.demo.entity;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;
    private int availableCopies;
    
    public Book() {
    }
    
    public Book(int id, String title, String author, String isbn, LocalDate publishedDate, int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.availableCopies = availableCopies;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
}
    public void setTitle(String title) {
        this.title = title;
}
    public String getTitle() {
        return title;
}
    public void setAuthor(String author) {
        this.author = author;
}
    public String getAuthor() {
        return author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
}
    public String getIsbn() {
        return isbn;
}
    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
}
    public LocalDate getPublishedDate() {
        return publishedDate;
}
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
}
    public int getAvailableCopies() {
        return availableCopies;
}
}