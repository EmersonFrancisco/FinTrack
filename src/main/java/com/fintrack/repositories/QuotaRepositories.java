package com.fintrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.model.Quota;

public interface QuotaRepositories extends JpaRepository<Quota, Integer> {

}
