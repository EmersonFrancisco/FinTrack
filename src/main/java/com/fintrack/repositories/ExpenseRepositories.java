package com.fintrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.expense.Expense;
import com.fintrack.model.user.User;

public interface ExpenseRepositories extends JpaRepository<Expense, Integer> {

	List<Expense> findAllByUser(User user);

}
