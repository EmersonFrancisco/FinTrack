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
import com.fintrack.exception.ExpenseValidateException;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.expense.ExpenseResponseDTO;
import com.fintrack.model.user.User;
import com.fintrack.service.ExpenseService;
import com.fintrack.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ExpenseService expenseService;
	
	@PatchMapping
	public ResponseEntity<Object> createOrUpdateQuota (
			@RequestHeader(required = true) String authToken,
			@RequestBody @Valid ExpenseRequestDTO  expenseRequest){
		
		try {
			
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			ExpenseResponseDTO expenseResponse = expenseService.validateAndCreateOrUpdateExpense(expenseRequest, user);
			
			return new ResponseEntity<Object>(expenseResponse, HttpStatus.CREATED);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (ExpenseValidateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a create new expense process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{idExpense}")
	public ResponseEntity<Object> deleteWallet (
			@RequestHeader(required = true) String authToken,
			@PathVariable Integer idExpense){
		
		try {
			
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			expenseService.validateAndDeleteExpense(idExpense, user);
			
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (ExpenseValidateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a delete expense process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> getAll (
			@RequestHeader(required = true) String authToken){
		try{
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			List<ExpenseResponseDTO> listExpenseResponse = expenseService.getAllExpenseByUser(user);
			
			return new ResponseEntity<Object>(listExpenseResponse, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a get all expense by user process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{idExpense}")
	public ResponseEntity<Object> getById (
			@RequestHeader(required = true) String authToken,
			@PathVariable Integer idExpense){
		try{
			User user = userService.verifyIntegrityAuthTokenAndGetUser(authToken);
			ExpenseResponseDTO walletResponse = expenseService.getExpenseResponseByid(idExpense, user);
			
			return new ResponseEntity<Object>(walletResponse, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED); 			
		} catch (ExpenseValidateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>("A error ocurred during a get Expense by id process", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
