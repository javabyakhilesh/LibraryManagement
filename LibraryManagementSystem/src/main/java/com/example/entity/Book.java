package com.example.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String title;

    @Column(unique = true)
    private String isbn;

    
    private LocalDate publisherDate;

    
    private Double price;

   
    private String lastModifiedBy;
    
    //A book is written by only one author.
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;

	public Book() {
		super();
		
	}

	public Book(Long id, String title, String isbn, LocalDate publisherDate, Double price, Long authorId,
			String createdBy, String lastModifiedBy) {
		super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.publisherDate = publisherDate;
		this.price = price;
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", publisherDate=" + publisherDate
				+ ", price=" + price + ", lastModifiedBy="
				+ lastModifiedBy + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getPublisherDate() {
		return publisherDate;
	}

	public void setPublisherDate(LocalDate publisherDate) {
		this.publisherDate = publisherDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
    
    
}