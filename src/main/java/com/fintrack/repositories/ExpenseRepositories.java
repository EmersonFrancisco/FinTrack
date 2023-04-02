package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.Expense;

public interface ExpenseRepositories extends JpaRepository<Expense, Integer> {

}
