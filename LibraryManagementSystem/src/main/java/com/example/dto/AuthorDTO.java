package com.example.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class AuthorDTO {
	
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    
	public AuthorDTO() {
		super();
		
	}

	public AuthorDTO(String firstName, String lastName, String email, LocalDate birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "AuthorDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + 
	             email + ", birthDate=" + birthDate + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
    
    
    
}
