package com.fintrack.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.fintrack.model.user.User;
import com.fintrack.model.user.UserBasicDTO;
import com.fintrack.model.user.UserResponseDTO;

public interface UserService {
	
	public UserResponseDTO createNewUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	public String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	public boolean deleteUser(Integer userId);

	public UserResponseDTO updateBasicDataUser(UserBasicDTO user);

	public List<UserResponseDTO> getAllUser();

	public UserResponseDTO getUserById(Integer userId);

}
