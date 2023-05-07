package com.fintrack.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintrack.enums.ExpenseStatus;
import com.fintrack.enums.ExpenseType;
import com.fintrack.exception.ExpenseValidateException;
import com.fintrack.model.Quota;
import com.fintrack.model.expense.Expense;
import com.fintrack.model.expense.ExpenseRequestDTO;
import com.fintrack.model.expense.ExpenseResponseDTO;
import com.fintrack.model.user.User;
import com.fintrack.repositories.ExpenseRepositories;

import jakarta.validation.Valid;

@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	ExpenseRepositories expenseRepositories;
	
	@Autowired
	QuotaService quotaService;
	
	@Autowired
	ModelMapper modelMapper;
	
	private Expense save(Expense expense) {
		return expenseRepositories.save(expense);
	}
	
	private void delete(Expense expense) {
		expenseRepositories.delete(expense);
	}

	@Override
	public ExpenseResponseDTO validateAndCreateOrUpdateExpense(@Valid ExpenseRequestDTO expenseRequest, User user) throws ExpenseValidateException {
		Expense expense;
		if(expenseRequest.getId() != null && expenseRequest.getId() > 0) {
			expense = expenseRepositories.findById(expenseRequest.getId()).orElse(null);
			if(expense != null) {
				updateExpenseAndQuotasIfNecessary(expense, expenseRequest);
			} else {
				throw new ExpenseValidateException("The idExpense not Exist or is deleted");
			}
		} else {
			expense = createNewExpenseAndQuotas(expenseRequest,user);
		}
		expense = save(expense);
		return convertToResponseDTO(expense);
	}
	
	private void updateExpenseAndQuotasIfNecessary(Expense expense, @Valid ExpenseRequestDTO expenseRequest) throws ExpenseValidateException {
		ExpenseType type = validateAndGetExpenseType(expenseRequest.getType());
		expense.setName(expenseRequest.getName());
		expense.setType(type);
		if(ExpenseStatus.PENDENT.equals(expense.getStatus())) {
			needChangeTotalAmountOrSizeQuotas(expense, expenseRequest);
			needChangePaymentDay(expense, expenseRequest);
			expense.setTotalAmount(expenseRequest.getTotalAmount());
			expense.setDayMonthPayment(expenseRequest.getDayMonthPayment());
		}
	}

	private void needChangeTotalAmountOrSizeQuotas(Expense expense, ExpenseRequestDTO expenseRequest) {
		if(hasChangeTotalAmoutOrSizeQuotas(expense, expenseRequest)) {
			Double amountPerQuota = expenseRequest.getTotalAmount() / expenseRequest.getAmountQuotas();
			needAddNewQuotas(expense, expenseRequest);
			for (Quota quota : expense.getQuotas()) {
				quota.setAmount(amountPerQuota);				
			}
		}
	}

	private void needChangePaymentDay(Expense expense, ExpenseRequestDTO expenseRequest) {
		if(!expense.getDayMonthPayment().equals(expenseRequest.getDayMonthPayment())) {
			for (Quota quota : expense.getQuotas()) {
				OffsetDateTime newPaymentDate = quota.getPaymentDate().withDayOfMonth(expenseRequest.getDayMonthPayment());
				quota.setPaymentDate(newPaymentDate);				
			}
		}
	}

	private boolean hasChangeTotalAmoutOrSizeQuotas(Expense expense, ExpenseRequestDTO expenseRequest) {
		return !expense.getTotalAmount().equals(expenseRequest.getTotalAmount()) || 
				expense.getQuotas().size() != expenseRequest.getAmountQuotas();
	}

	private void needAddNewQuotas(Expense expense, ExpenseRequestDTO expenseRequest) {
		if(expense.getQuotas().size() != expenseRequest.getAmountQuotas()) {
			Integer presentSize = expense.getQuotas().size();
			Integer newSize = expenseRequest.getAmountQuotas();
			for (int i = presentSize + 1; i <= newSize; i ++) {
				expense.getQuotas().add(quotaService.createNewQuotaToAddListByExpense(expense, i));
			}
		}
	}

	private ExpenseResponseDTO convertToResponseDTO(Expense expense) {
		return modelMapper.map(expense, ExpenseResponseDTO.class);
	}

	private Expense createNewExpenseAndQuotas(@Valid ExpenseRequestDTO expenseRequest, User user) throws ExpenseValidateException {
		Expense expense = new Expense();
		ExpenseType type = validateAndGetExpenseType(expenseRequest.getType());
		expense.setName(expenseRequest.getName());
		expense.setUser(user);
		expense.setDayMonthPayment(expenseRequest.getDayMonthPayment());
		expense.setStatus(ExpenseStatus.PENDENT);
		expense.setType(type);
		expense.setTotalAmount(expenseRequest.getTotalAmount());
		expense = save(expense);
		List<Quota> quotaList = quotaService.createListQuotasByExpenseRequest(expenseRequest, expense);
		expense.setQuotas(quotaList);
		return expense;
	}

	private ExpenseType validateAndGetExpenseType(String type) throws ExpenseValidateException {
		if(!ExpenseType.FIXED.name().equals(type) &&
		   !ExpenseType.FINANCED.name().equals(type) &&
		   !ExpenseType.CREDIT_CARD.name().equals(type)){
			throw new ExpenseValidateException("The Expense type entered is not a valid type!");
		}
		return ExpenseType.valueOf(type);
	}

	@Override
	public void validateAndDeleteExpense(Integer idExpense, User user) throws ExpenseValidateException {
		Expense expense = expenseRepositories.findById(idExpense).orElse(null);
		if(expenseExistAndIsItFromTheUser(user, expense)) {
				delete(expense);
		} else {
			throw new ExpenseValidateException("The idExpense not Exist or is deleted or, this expense don't is your user!");
		}
	}

	@Override
	public List<ExpenseResponseDTO> getAllExpenseByUser(User user) {
		List<Expense> listExpense = expenseRepositories.findAllByUser(user);
		List<ExpenseResponseDTO> listExpenseResponse = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(listExpense)) {
			for(Expense expense : listExpense) {
				listExpenseResponse.add(convertToResponseDTO(expense));
			}
		}
		return listExpenseResponse;
	}

	@Override
	public ExpenseResponseDTO getExpenseResponseByid(Integer idExpense, User user) throws ExpenseValidateException {
		Expense expense = expenseRepositories.findById(idExpense).orElse(null);
		if (expenseExistAndIsItFromTheUser(user, expense)) {
			return convertToResponseDTO(expense);
		} else {
			throw new ExpenseValidateException("The idExpense not Exist or is deleted or, this expense don't is your user!");
		}
	}
	
	private boolean expenseExistAndIsItFromTheUser(User user, Expense expense) {
		return expense != null && expense.getUser().equals(user);
	}
	
}

