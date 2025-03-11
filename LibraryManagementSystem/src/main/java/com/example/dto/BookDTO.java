package com.example.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class BookDTO {
	
	private String title;
    private String isbn;
    private LocalDate publisherDate;
    private Double price;
    private Long authorId;
    private String createdBy;
    private String lastModifiedBy;
    
	public BookDTO() {
		super();
		
	}

	public BookDTO(String title, String isbn, LocalDate publisherDate, Double price, 
			Long authorId, String createdBy, String lastModifiedBy) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.publisherDate = publisherDate;
		this.price = price;
		this.authorId = authorId;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "BookDTO [title=" + title + ", isbn=" + isbn + ", publisherDate=" + publisherDate + ", "
				+ "price=" + price + ", authorId=" + authorId + ", createdBy=" + createdBy + ","
						+ " lastModifiedBy=" + lastModifiedBy + "]";
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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
    
	
    
	 
	 

}
