package com.fintrack.service;

import java.util.List;

import com.fintrack.model.expense.Expense;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.quota.Quota;
import com.fintrack.model.quota.QuotaRequestEditListDTO;
import com.fintrack.model.user.User;

public interface QuotaService {

	List<Quota> createListQuotasByExpenseRequest(ExpenseRequestDTO expenseRequest, Expense expense);

	Quota createNewQuotaToAddListByExpense(Expense expense, Integer indexList);

	List<Quota> validateAndUpdateListQuotas(Expense expense, QuotaRequestEditListDTO quotaEditListRequest, User user);

	void cleanUnusedQuotas(List<Quota> listQuota, Expense expense);

	void deteleByQuota(Quota quotaToDelete);

}
