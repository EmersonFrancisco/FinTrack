package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.CreditCard;

public interface CreditCardRepositories extends JpaRepository<CreditCard, Integer>{

}
