package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BookDTO;
import com.example.entity.Book;
import com.example.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}

	public Book getBookById(Long id) {
		 Book book = bookRepository.findById(id).orElseThrow(()
				 ->new RuntimeException("Book Not Found"));
		 return book;
		 
	}
	
	public Book addBook(BookDTO bookDTO) {
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setIsbn(bookDTO.getIsbn());
		/*
		 * book.setCreatedBy(bookDTO.getCreatedBy());
		 * book.setAuthorId(bookDTO.getAuthorId());
		 */
		book.setLastModifiedBy(bookDTO.getLastModifiedBy());
		book.setPublisherDate(bookDTO.getPublisherDate());
		book.setPrice(bookDTO.getPrice());
		return bookRepository.save(book);
	}
	
	public Book updateBook(Long id, BookDTO bookDTO) {
		Book oldBook = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book Not Found"));
		
		oldBook.setTitle(bookDTO.getTitle());
		oldBook.setIsbn(bookDTO.getIsbn());
		/*
		 * oldBook.setCreatedBy(bookDTO.getCreatedBy());
		 * oldBook.setAuthorId(bookDTO.getAuthorId());
		 */
		oldBook.setLastModifiedBy(bookDTO.getLastModifiedBy());
		oldBook.setPublisherDate(bookDTO.getPublisherDate());
		oldBook.setPrice(bookDTO.getPrice());
		return bookRepository.save(oldBook);
		
	}
	
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}
	

}
