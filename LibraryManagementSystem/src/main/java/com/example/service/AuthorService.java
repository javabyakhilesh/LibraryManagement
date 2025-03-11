package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.AuthorDTO;
import com.example.entity.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	

	    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
        }
	    
	    public Author getAuthorById(Long id) {
	         Author author = authorRepository.findById(id).orElseThrow(()
	        		 ->new RuntimeException("Author Not Found"));
	         return author;
	    }

	    public Author addAuthor(AuthorDTO authorDTO) {
			 Author author = new Author();
			 author.setFirstName(authorDTO.getFirstName());
			 author.setLastName(authorDTO.getLastName());
			 author.setEmail(authorDTO.getEmail());
			 author.setBirthDate(authorDTO.getBirthDate());
			 return authorRepository.save(author);
		}
	    
	    public Author updateAuthor(Long id, AuthorDTO authorDTO) {
	        Author existingAuthor = authorRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Author not found"));
            existingAuthor.setFirstName(authorDTO.getFirstName());
	        existingAuthor.setLastName(authorDTO.getLastName());
	        existingAuthor.setEmail(authorDTO.getEmail());
	        existingAuthor.setBirthDate(authorDTO.getBirthDate());

	        return authorRepository.save(existingAuthor);
	    }

	    public void deleteAuthor(Long id) {
	        authorRepository.deleteById(id);
	    }

}
