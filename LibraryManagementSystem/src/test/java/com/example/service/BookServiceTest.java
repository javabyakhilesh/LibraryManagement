package com.example.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import com.example.dto.BookDTO;
import com.example.entity.Book;
import com.example.repository.BookRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Spring Boot Guide");
        book.setIsbn("ISBN-12345");
        book.setPublisherDate(LocalDate.of(2024, 3, 10));
        book.setPrice(500.99);
        book.setLastModifiedBy("Editor");

        bookDTO = new BookDTO();
        bookDTO.setTitle("Updated Spring Boot Guide");
        bookDTO.setIsbn("ISBN-67890");
        bookDTO.setPublisherDate(LocalDate.of(2024, 3, 15));
        bookDTO.setPrice(599.99);
        bookDTO.setLastModifiedBy("Updated Editor");
    }

    
    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        List<Book> books = bookService.getAllBooks();

        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals("Spring Boot Guide", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    
    @Test
    void testGetBookById_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(1L);

        assertNotNull(foundBook);
        assertEquals("Spring Boot Guide", foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    
    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.getBookById(2L);
        });

        assertEquals("Book Not Found", exception.getMessage());
        verify(bookRepository, times(1)).findById(2L);
    }

    
    @Test
    void testAddBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.addBook(bookDTO);

        assertNotNull(createdBook);
        assertEquals("Spring Boot Guide", createdBook.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    
    @Test
    void testUpdateBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book updatedBook = bookService.updateBook(1L, bookDTO);

        assertNotNull(updatedBook);
        assertEquals("Updated Spring Boot Guide", updatedBook.getTitle());
        assertEquals("ISBN-67890", updatedBook.getIsbn());
        assertEquals("Updated Editor", updatedBook.getLastModifiedBy());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    
    @Test
    void testUpdateBook_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.updateBook(2L, bookDTO);
        });

        assertEquals("Book Not Found", exception.getMessage());
        verify(bookRepository, times(1)).findById(2L);
        verify(bookRepository, never()).save(any(Book.class));
    }

    
    @Test
    void testDeleteBookById() {
        doNothing().when(bookRepository).deleteById(1L);

        assertDoesNotThrow(() -> bookService.deleteBookById(1L));

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
