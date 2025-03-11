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

import com.example.dto.AuthorDTO;
import com.example.entity.Author;
import com.example.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	

        @GetMapping("/getAllAuthor")
        public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
        }
    
        @GetMapping("/getAuthorById/{id}")
        public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
    	return ResponseEntity.ok(authorService.getAuthorById(id));
        }
	
	    @PostMapping("/addAuthor")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDTO authorDTO) {
	        return ResponseEntity.ok(authorService.addAuthor(authorDTO));
	    }
	     
	    @PutMapping("/updateAuthor/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
	        return ResponseEntity.ok(authorService.updateAuthor(id, authorDTO));
	    }

	    
	    @DeleteMapping("/deleteAuthor/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
	        authorService.deleteAuthor(id);
	        return ResponseEntity.ok().build();
	    }
}
