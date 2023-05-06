package com.fintrack.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintrack.model.user.UserBasicDTO;
import com.fintrack.model.user.UserRequestDTO;
import com.fintrack.model.user.UserResponseDTO;
import com.fintrack.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(headers="Accept=application/json", produces="application/json;charset=UTF-8")
	public ResponseEntity<Object> create(@Valid @RequestBody UserRequestDTO userBasic) {
		try {
			UserResponseDTO userResponseDTO = userService.createNewUser(userBasic);
			return new ResponseEntity<Object>(userResponseDTO, HttpStatus.CREATED);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping(headers="Accept=application/json", produces="application/json;charset=UTF-8")
	public ResponseEntity<Object> update(@Valid @RequestBody UserBasicDTO userBasicDTO) {
	    UserResponseDTO userResponseDTO = userService.updateBasicDataUser(userBasicDTO);
	    return new ResponseEntity<Object>(userResponseDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> delete(@PathVariable Integer userId) {
		if(userService.deleteUser(userId)) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(String.format("Not found User by Id [%d]",userId), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<Object> getAll(){
		List<UserResponseDTO> listUserDtos = userService.getAllUser();
		return new ResponseEntity<Object>(listUserDtos, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getAll(@PathVariable Integer userId){
		UserResponseDTO UserDto = userService.getUserById(userId);
		return new ResponseEntity<Object>(UserDto, HttpStatus.OK);
	}

}
