package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.Payment;

public interface PaymentRepositories extends JpaRepository<Payment, Integer> {

}
