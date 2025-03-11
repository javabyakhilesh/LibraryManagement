package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDTO;
import com.example.entity.Book;
import com.example.service.BookService;
@RequestMapping("/books")
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("getAllBooks")
	public ResponseEntity<List<Book>> getAllBook(){
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@GetMapping("getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
		return ResponseEntity.ok(bookService.getBookById(id));
	}
	
	
	@PostMapping("/addBook")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO){
		return ResponseEntity.ok(bookService.addBook(bookDTO));
	}
	
	@PutMapping("/updateBook/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> updateBook(@PathVariable Long id,  @RequestBody BookDTO bookDTO){
		return ResponseEntity.ok(bookService.updateBook(id , bookDTO));
	}
	
	@DeleteMapping("/deleteBook/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id){
		bookService.deleteBookById(id);
		return ResponseEntity.ok().build();
	}
	
}
