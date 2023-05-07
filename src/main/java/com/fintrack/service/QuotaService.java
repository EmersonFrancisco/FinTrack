package com.fintrack.service;

import java.util.List;

import com.fintrack.model.Quota;
import com.fintrack.model.expense.Expense;
import com.fintrack.model.expense.ExpenseRequestDTO;

import jakarta.validation.Valid;

public interface QuotaService {

	List<Quota> createListQuotasByExpenseRequest(@Valid ExpenseRequestDTO expenseRequest, Expense expense);

	Quota createNewQuotaToAddListByExpense(Expense expense, Integer indexList);

}
