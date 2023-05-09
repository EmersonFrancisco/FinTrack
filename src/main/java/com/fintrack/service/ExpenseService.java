package com.fintrack.service;

import java.util.List;

import com.fintrack.exception.ExpenseValidateException;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.expense.ExpenseResponseDTO;
import com.fintrack.model.quota.Quota;
import com.fintrack.model.quota.QuotaRequestEditListDTO;
import com.fintrack.model.user.User;

public interface ExpenseService {

	ExpenseResponseDTO validateAndCreateOrUpdateExpense(ExpenseRequestDTO expenseRequest, User user) throws ExpenseValidateException;

	void validateAndDeleteExpense(Integer idExpense, User user) throws ExpenseValidateException;

	List<ExpenseResponseDTO> getAllExpenseByUser(User user);

	ExpenseResponseDTO getExpenseResponseByid(Integer idExpense, User user) throws ExpenseValidateException;

	List<Quota> validateAndUpdateListQuotas(QuotaRequestEditListDTO quotaEditListRequest, User user);

}
