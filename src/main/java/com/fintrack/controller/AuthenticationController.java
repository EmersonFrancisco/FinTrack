package com.fintrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintrack.exception.AuthenticationException;
import com.fintrack.service.UserService;

@RestController()
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<Object> login(@RequestHeader(value = "username", required = true) String username,
										@RequestHeader(value = "password", required = true) String password) {
		try {
			String authToken = userService.validateCredencialsAndMakeAuthToken(username, password);
		
			return new ResponseEntity<Object>(authToken, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
