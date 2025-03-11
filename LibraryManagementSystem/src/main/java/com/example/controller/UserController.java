package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @GetMapping("getAllUsers")
	    public ResponseEntity<List<User>> getAllUsers() {
	         return  ResponseEntity.ok(userService.getAllUsers());
	    }

	    
	    @GetMapping("/getUserById/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	         return  ResponseEntity.ok(userService.getUserById(id));
	    }

	    
	    @PostMapping("/addUser")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
	        return ResponseEntity.ok(userService.createUser(userDTO));
	    }

	    
	    @PutMapping("/updateUser/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
	        return ResponseEntity.ok(userService.updateUser(id,userDTO));
	    }

	  
	    @DeleteMapping("/deleteUser/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    	userService.deleteUser(id);
	        return ResponseEntity.ok().build();
	    }

	    
	    @PatchMapping("/{id}/change-role")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<User> changeUserRole(@PathVariable Long id, @RequestBody Set<String> roles) {
	        return ResponseEntity.ok(userService.changeUserRole(id,roles));
	    }

}
