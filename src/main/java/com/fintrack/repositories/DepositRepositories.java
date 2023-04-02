package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.Deposit;

public interface DepositRepositories extends JpaRepository<Deposit, Integer> {

}
