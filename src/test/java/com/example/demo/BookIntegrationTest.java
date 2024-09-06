//package com.example.demo;
//
//import com.example.demo.entity.Book;
//import com.example.demo.repo.BookRepository;
//import com.example.demo.service.BookService;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Sql(scripts = "/data.sql") // Optional: Pre-load data for testing
//public class BookIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @MockBean
//    private BookService bookService;
//
//
//    @Test
//    public void testGetAllBooks() throws Exception {
//        Book book1 = new Book(1, "Book1", "Author1", "12345", null, 10);
//        Book book2 = new Book(2, "Book2", "Author2", "67890", null, 5);
//
//        Mockito.when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));
//
//        mockMvc.perform(get("/api/books/get")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("Book1"))
//                .andExpect(jsonPath("$[1].title").value("Book2"))
//                .andDo(print());
//    }
//
//    @Test
//    public void testGetBookById() throws Exception {
//        Book book = new Book(1, "Book1", "Author1", "12345", null, 10);
//
//        Mockito.when(bookService.getBookById(1)).thenReturn(book);
//
//        mockMvc.perform(get("/api/books/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Book1"))
//                .andDo(print());
//    }
//
//    @Test
//    public void testSaveBook() throws Exception {
//        mockMvc.perform(post("/api/books/save")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"title\": \"Book1\", \"author\": \"Author1\", \"isbn\": \"12345\", \"availableCopies\": 10}"))
//                .andExpect(status().isCreated())
//                .andDo(print());
//    }
//    @Test
//    public void testFindAllBooks() {
//        List<Book> books = bookRepository.findAll();
//        assertNotNull(books);
//        assertTrue(books.size() > 0);
//    }
//
//    @Test
//    public void testFindBookById() {
//        Book book = bookRepository.findById(1);
//        assertNotNull(book);
//        assertEquals("Book Title", book.getTitle());
//    }
//
//    @Test
//    public void testSaveBookToRepository() {
//        Book newBook = new Book();
//        newBook.setTitle("New Book");
//        newBook.setAuthor("New Author");
//        newBook.setIsbn("11111");
//        newBook.setPublishedDate(LocalDate.now());
//        newBook.setAvailableCopies(5);
//
//        bookRepository.saveBook(newBook);
//
//        List<Book> books = bookRepository.findAll();
//        assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("New Book")));
//    }
//
//    @Test
//    public void testUpdateBookInRepository() {
//        Book book = bookRepository.findById(1);
//        book.setAvailableCopies(8);
//        bookRepository.update(book);
//
//        Book updatedBook = bookRepository.findById(1);
//        assertEquals(8, updatedBook.getAvailableCopies());
//    }
//
//    @Test
//    public void testDeleteBookFromRepository() {
//        bookRepository.deleteById(1);
//
//        List<Book> books = bookRepository.findAll();
//        assertTrue(books.stream().noneMatch(b -> b.getId() == 1));
//    }
//}
