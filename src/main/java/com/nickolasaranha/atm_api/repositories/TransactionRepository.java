package com.nickolasaranha.atm_api.repositories;

import com.nickolasaranha.atm_api.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
