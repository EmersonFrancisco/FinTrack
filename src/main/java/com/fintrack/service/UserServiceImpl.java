package com.fintrack.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintrack.exception.AuthenticationException;
import com.fintrack.exception.UserValidateException;
import com.fintrack.model.user.User;
import com.fintrack.model.user.UserBasicDTO;
import com.fintrack.model.user.UserResponseDTO;
import com.fintrack.repositories.UserRepositories;
import com.fintrack.utils.PropertiesReader;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String _02X = "%02X";
	private static final String SHA_256 = "SHA-256";
	private static final String UTF_8 = "UTF-8";
	private static String aleatoryMd5Pt1 = PropertiesReader.getPropertiesByKey("aleatory.md5.pt1");
	private static String aleatoryMd5Pt2 = PropertiesReader.getPropertiesByKey("aleatory.md5.pt2");
	
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
	 MessageDigest algoritmo = MessageDigest.getInstance(SHA_256);
	 String aleatoryConcatPassword = aleatoryMd5Pt1+password+aleatoryMd5Pt2;
	 byte digestMessage[] = algoritmo.digest(aleatoryConcatPassword.getBytes(UTF_8));
	 StringBuilder hexPassword = new StringBuilder();
	 for (byte aByte : digestMessage) {
	    hexPassword.append(String.format(_02X, 0xFF & aByte));
	 }
	 return hexPassword.toString();
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

	@Override
	public String validateCredencialsAndMakeAuthToken(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			User user = userRepositories.findByUsername(username);
			if(user != null) {
				return validatePassword(user, password);
			}
			throw new AuthenticationException("Invalid Username");
		}
		return StringUtils.EMPTY;
	}

	private String validatePassword(User user, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String encryptedPassword = encryptPassword(password);
		if(user.getPassword().equals(encryptedPassword)) {
			String authToken = makeAuthenticationHashToken(user);
			user.setAuthToken(authToken);
			save(user);
			return authToken;
		}
		throw new AuthenticationException("Invalid Password");
	}

	private String makeAuthenticationHashToken(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String fullInfoToHash = user.getId()+user.getUsername()+user.getCreateDate()+new Date();
		return encryptPassword(fullInfoToHash);
	}
	
	@Override
	public User verifyIntegrityAuthTokenAndGetUser(String authToken) {
		User user = userRepositories.findByAuthToken(authToken);
		if(user != null) {
			return user;
		}
		throw new AuthenticationException("This Token does not exist or is no longer avaliable");
	}

}
