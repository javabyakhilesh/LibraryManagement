package com.example.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
    private Boolean enabled;
    
}
