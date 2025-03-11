package com.example.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.LoginRequestDTO;
import com.example.dto.LoginResponseDTO;
import com.example.dto.RegisterRequestDTO;
import com.example.entity.User;
import com.example.jwt.JwtService;
import com.example.repository.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
		
		if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("User already registered");
		}
		
		Set<String> roles = new HashSet<>();
		roles.add("ROLE_USER");
		
		User user = new User();
		
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordencoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);
		return userRepository.save(user);
	}
	
	public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
		
		if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("User already registered");
		}
		
		Set<String> roles = new HashSet<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordencoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);
		return userRepository.save(user);
	}

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		
		//User Authentication Process
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTO.getUsername(), 
						loginRequestDTO.getPassword())
				);
		User user =userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(
				() ->new RuntimeException("User Not Found"));
		
		//fetch JWT Token
		String token = jwtService.generateToken(user);
		
		return LoginResponseDTO.builder()
				.token(token)
				.username(user.getUsername())
				.roles(user.getRoles())
				.build();
		
			}
}
