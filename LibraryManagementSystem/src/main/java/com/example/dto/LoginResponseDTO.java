package com.example.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponseDTO {

	private String token;
	private String username;
	private Set<String> roles;
	
	
	public LoginResponseDTO() {
		super();
		
	}
	public LoginResponseDTO(String token, String username, Set<String> roles) {
		super();
		this.token = token;
		this.username = username;
		this.roles = roles;
	}
	
	
	@Override
	public String toString() {
		return "LoginResponseDTO [token=" + token + ", username=" + username + ", roles=" + roles + "]";
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
}
