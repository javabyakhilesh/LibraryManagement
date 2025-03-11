package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequestDTO {
    
	private String username;
	private String password;
	
	
}
