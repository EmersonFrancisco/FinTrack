package com.fintrack.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintrack.enums.ExpenseStatus;
import com.fintrack.exception.QuotaValidateException;
import com.fintrack.model.expense.Expense;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.quota.Quota;
import com.fintrack.model.quota.QuotaRequestDTO;
import com.fintrack.model.quota.QuotaRequestEditListDTO;
import com.fintrack.model.user.User;
import com.fintrack.repositories.QuotaRepositories;

@Service("quotaService")
public class QuotaServiceImpl implements QuotaService {
	
	private static final double DOUBLE_ZERO = 0.0;
	private static final int ZERO = 0;

	@Autowired
	QuotaRepositories quotaRepositories;
	
	private Quota save(Quota quota) {
		return quotaRepositories.save(quota);
	}
	
	private void delete(Quota quota) {
		quotaRepositories.delete(quota);
	}

	@Override
	public List<Quota> createListQuotasByExpenseRequest(ExpenseRequestDTO expenseRequest, Expense expense) {
		Double totalAmount = expenseRequest.getTotalAmount();
		Double ValuePerQuota = totalAmount / expenseRequest.getAmountQuotas();
		List<Quota> quotaList = new ArrayList<>();
		for (int i=1;i<=expenseRequest.getAmountQuotas(); i++) {
			Quota quota = new Quota();
			quota.setExpense(expense);
			quota.setAmount(ValuePerQuota);
			quota.setPaymentDate(getPaymentDay(i, expenseRequest.getDayMonthPayment()));
			quota.setStatus(ExpenseStatus.PENDENT);
			quota.setTotalPaid(DOUBLE_ZERO);
			quotaList.add(quota);
		}
		return quotaList;
	}

	private OffsetDateTime getPaymentDay(int i, Integer dayMonthPayment) {
		OffsetDateTime dateTime = OffsetDateTime.now().plusMonths(i);
		dateTime = dateTime.withDayOfMonth(dayMonthPayment);
		dateTime = dateTime.withHour(ZERO);
		dateTime = dateTime.withMinute(ZERO);
		dateTime = dateTime.withSecond(ZERO);
		dateTime = dateTime.withNano(ZERO);
		return dateTime;
	}

	@Override
	public Quota createNewQuotaToAddListByExpense(Expense expense, Integer indexList) {
		Quota quota = new Quota();
		quota.setExpense(expense);
		quota.setPaymentDate(getPaymentDay(indexList, expense.getDayMonthPayment()));	
		quota.setStatus(ExpenseStatus.PENDENT);
		quota.setTotalPaid(DOUBLE_ZERO);
		return quota;		
	}

	@Override
	public List<Quota> validateAndUpdateListQuotas(Expense expense, QuotaRequestEditListDTO quotaEditListRequest, User user) {
		List<Quota> listQuota = new ArrayList<>();
		if(expenseExistAndIsFromUser(expense, user)) {
			if(expense.getTotalAmount() == quotaEditListRequest.getTotalAmountByListQuotasRequestDTO()) {
				for(QuotaRequestDTO quotaRequest : quotaEditListRequest.getListQuotasRequest()) {
					Quota quota = new Quota();
					if(quotaRequest.getId() != null && quotaRequest.getId() > 0) {
						quota = quotaRepositories.findById(quotaRequest.getId()).orElse(null);
						if(quotaExistsAndIsFromEqualExpense(quota, expense) && verifyIfQuotaIsPendentOrNotChangeInfos(quota, quotaRequest)) {
							quota.setAmount(quotaRequest.getAmount());
							quota.setPaymentDate(quotaRequest.getPaymentDate());
							listQuota.add(quota);
						} else {
							throw new QuotaValidateException("one of the quotas informed in the list does not belong to the funding of the request or is not in pending status and an attempt was made to change the data");
						}
					} else {
						quota.setAmount(quotaRequest.getAmount());
						quota.setPaymentDate(quota.getPaymentDate());
						quota.setStatus(ExpenseStatus.PENDENT);
						quota.setExpense(expense);
						quota.setTotalPaid(DOUBLE_ZERO);
						listQuota.add(quota);
					}
				}
				for(Quota quota : listQuota) {
					Integer id = save(quota).getId();
					quota.setId(id);
				}
			} else {
				throw new QuotaValidateException("the total amount of quotas in the list is not equal to that defined in the expense default setting");
			}
		} else {
			throw new QuotaValidateException("The reported expense does not belong to the user or does not exist");
		}
		return listQuota;
	}

	private boolean verifyIfQuotaIsPendentOrNotChangeInfos(Quota quota, QuotaRequestDTO quotaRequest) {
		if(!ExpenseStatus.PENDENT.equals(quota.getStatus())) {
			if(quota.getAmount() != quotaRequest.getAmount()) {
				return false;
			}
			if(quota.getPaymentDate() != quotaRequest.getPaymentDate()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void cleanUnusedQuotas(List<Quota> listQuota, Expense expense) {
		for(Quota quota : expense.getQuotas()) {
			if(!listQuota.contains(quota)) {
				delete(quota);
			}
		}
	}

	private boolean quotaExistsAndIsFromEqualExpense(Quota quota, Expense expense) {
		return quota != null  && quota.getExpense().equals(expense);
	}

	private boolean expenseExistAndIsFromUser(Expense expense, User user) {
		return expense != null &&
			   expense.getUser().equals(user);
	}

	@Override
	public void deteleByQuota(Quota quotaToDelete) {
		delete(quotaToDelete);
	}

}