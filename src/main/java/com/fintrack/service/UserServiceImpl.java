package com.fintrack.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintrack.exception.UserValidateException;
import com.fintrack.model.user.User;
import com.fintrack.model.user.UserBasicDTO;
import com.fintrack.model.user.UserResponseDTO;
import com.fintrack.repositories.UserRepositories;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String ALEATORY_MD5_PT1 = "8E059C750B86ACC";
	private static final String ALEATORY_MD5_PT2 = "B40939C1A361FC4B1";
	
	@Autowired
	UserRepositories userRepositories;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	private User save(User user) {
		return userRepositories.save(user);
	}
	
	private void delete(User user) {
		userRepositories.delete(user);
	}

	@Override
	public UserResponseDTO createNewUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		validateIfUsernameExist(user.getUsername());
		encriptPassword(user);
		user = save(user);
		return toUserResponseDTO(user);
	}
	
	private void validateIfUsernameExist(String username) {
		if (userRepositories.existsByUsername(username)) {
			throw new UserValidateException("username exist");
		}
	}

	private void encriptPassword(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String encriptedPassword = encryptPassword(user.getPassword());
		user.setPassword(encriptedPassword);
	}
	
	@Override
	public String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	 MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
	 byte digestMessage[] = algoritmo.digest(password.getBytes("UTF-8"));
	 StringBuilder hexPassword = new StringBuilder();
	 for (byte aByte : digestMessage) {
	    hexPassword.append(String.format("%02X", 0xFF & aByte));
	 }
	 return ALEATORY_MD5_PT1+hexPassword.toString()+ALEATORY_MD5_PT2;
	}

	@Override
	public boolean deleteUser(Integer userId) {
		Optional<User> user = userRepositories.findById(userId);
		if(user.isPresent()) {
			delete(user.get());
			return true;
		}
		return false;
	}

	@Override
	public UserResponseDTO updateBasicDataUser(UserBasicDTO userBasicDTO) {
		Optional<User> optionalUser = userRepositories.findById(userBasicDTO.getId());
		if(optionalUser.isPresent()) {
			optionalUser.get().setName(userBasicDTO.getName());
			optionalUser.get().setSurname(userBasicDTO.getSurname());
			User user = save(optionalUser.get());
			return toUserResponseDTO(user);
		} else {
			throw new UserValidateException(String.format("User by id [%d] not exist or is Deleted", userBasicDTO.getId()));
		}
	}
	
	public UserResponseDTO toUserResponseDTO(User user) {
		return modelMapper.map(user, UserResponseDTO.class);
	}

	@Override
	public List<UserResponseDTO> getAllUser() {
		List<User> listUsers = userRepositories.findAll();
		List<UserResponseDTO> listUserDTOs = new ArrayList<>();
		for(User user : listUsers) {
			listUserDTOs.add(toUserResponseDTO(user));
		}
		return listUserDTOs;
	}

	@Override
	public UserResponseDTO getUserById(Integer userId) {
		Optional<User> optionalUser = userRepositories.findById(userId);
		if(optionalUser.isPresent()) {
			return toUserResponseDTO(optionalUser.get());
		} else {
			throw new UserValidateException(String.format("User by id [%d] not exist or is Deleted", userId));
		}
	}

}
