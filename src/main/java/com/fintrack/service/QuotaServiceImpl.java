package com.fintrack.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintrack.enums.ExpenseStatus;
import com.fintrack.model.expense.Expense;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.quota.Quota;
import com.fintrack.repositories.QuotaRepositories;

import jakarta.validation.Valid;

@Service("quotaService")
public class QuotaServiceImpl implements QuotaService {
	
	@Autowired
	QuotaRepositories quotaRepositories;
	
	private Quota save(Quota quota) {
		return quotaRepositories.save(quota);
	}
	
	private void delete(Quota quota) {
		quotaRepositories.delete(quota);
	}

	@Override
	public List<Quota> createListQuotasByExpenseRequest(@Valid ExpenseRequestDTO expenseRequest, Expense expense) {
		Double totalAmount = expenseRequest.getTotalAmount();
		Double ValuePerQuota = totalAmount / expenseRequest.getAmountQuotas();
		List<Quota> quotaList = new ArrayList<>();
		for (int i=1;i<=expenseRequest.getAmountQuotas(); i++) {
			Quota quota = new Quota();
			quota.setExpense(expense);
			quota.setAmount(ValuePerQuota);
			quota.setPaymentDate(getPaymentDay(i, expenseRequest.getDayMonthPayment()));
			quota.setQuotaNumber(i);
			quota.setStatus(ExpenseStatus.PENDENT);
			quota.setTotalPaid(0.0);
			quotaList.add(quota);
		}
		return quotaList;
	}

	private OffsetDateTime getPaymentDay(int i, Integer dayMonthPayment) {
		OffsetDateTime dateTime = OffsetDateTime.now().plusMonths(i);
		dateTime = dateTime.withDayOfMonth(dayMonthPayment);
		dateTime = dateTime.withHour(0);
		dateTime = dateTime.withMinute(0);
		dateTime = dateTime.withSecond(0);
		dateTime = dateTime.withNano(0);
		return dateTime;
	}

	@Override
	public Quota createNewQuotaToAddListByExpense(Expense expense, Integer indexList) {
		Quota quota = new Quota();
		quota.setExpense(expense);
		quota.setPaymentDate(getPaymentDay(indexList, expense.getDayMonthPayment()));
		quota.setQuotaNumber(indexList);
		quota.setStatus(ExpenseStatus.PENDENT);
		quota.setTotalPaid(0.0);
		return quota;		
	}

}

