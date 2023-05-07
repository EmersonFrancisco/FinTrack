package com.fintrack.service;

import java.util.List;

import com.fintrack.exception.ExpenseValidateException;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.expense.ExpenseResponseDTO;
import com.fintrack.model.user.User;

import jakarta.validation.Valid;

public interface ExpenseService {

	ExpenseResponseDTO validateAndCreateOrUpdateExpense(@Valid ExpenseRequestDTO expenseRequest, User user) throws ExpenseValidateException;

	void validateAndDeleteExpense(Integer idExpense, User user) throws ExpenseValidateException;

	List<ExpenseResponseDTO> getAllExpenseByUser(User user);

	ExpenseResponseDTO getExpenseResponseByid(Integer idExpense, User user) throws ExpenseValidateException;

}
