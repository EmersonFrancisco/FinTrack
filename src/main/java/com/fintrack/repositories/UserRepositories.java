package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.user.User;

public interface UserRepositories extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);

}
