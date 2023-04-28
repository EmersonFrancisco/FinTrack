package com.fintrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.user.User;
import com.fintrack.model.wallet.Wallet;

public interface WalletRepositories extends JpaRepository<Wallet, Integer> {

	List<Wallet> findAllByUser(User user);

}
