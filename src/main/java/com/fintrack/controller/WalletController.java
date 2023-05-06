package com.fintrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintrack.exception.AuthenticationException;
import com.fintrack.exception.WalletValidateException;
import com.fintrack.model.user.User;
import com.fintrack.model.wallet.WalletRequestDTO;
import com.fintrack.model.wallet.WalletResponseDTO;
import com.fintrack.service.UserService;
import com.fintrack.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	WalletService walletService;
	
	@PatchMapping
	public ResponseEntity<Object> createOrUpdateWallet (
			@RequestHeader(required = true) String authToken,
			@RequestBody WalletRequestDTO walletRequest){
		
		try {
			
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			WalletResponseDTO walletResponse = walletService.validateAndCreateOrUpdateWallet(walletRequest, user);
			
			return new ResponseEntity<Object>(walletResponse, HttpStatus.CREATED);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (WalletValidateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(String.format("A error ocurred during a create new wallet process, ERROR:%s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{idWallet}")
	public ResponseEntity<Object> deleteWallet (
			@RequestHeader(required = true) String authToken,
			@PathVariable Integer idWallet){
		
		try {
			
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			walletService.validateAndDeleteWallet(idWallet, user);
			
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (WalletValidateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a delete wallet process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> getAll (
			@RequestHeader(required = true) String authToken){
		try{
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			List<WalletResponseDTO> listWalletResponse = walletService.getAllWalletResponseByUser(user);
			
			return new ResponseEntity<Object>(listWalletResponse, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a get all wallet by user process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{idWallet}")
	public ResponseEntity<Object> getById (
			@RequestHeader(required = true) String authToken,
			@PathVariable Integer idWallet){
		try{
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			WalletResponseDTO walletResponse = walletService.getWalletResponseById(idWallet, user);
			
			return new ResponseEntity<Object>(walletResponse, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (WalletValidateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a get wallet by id process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
