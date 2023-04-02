package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.Wallet;

public interface WalletRepositories extends JpaRepository<Wallet, Integer> {

}
