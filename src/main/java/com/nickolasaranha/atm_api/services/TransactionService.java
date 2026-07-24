package com.nickolasaranha.atm_api.services;

import com.nickolasaranha.atm_api.entities.Transaction;
import com.nickolasaranha.atm_api.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
  private TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
      this.transactionRepository = transactionRepository;
  }

  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }

  public Transaction findById(Long id) {
    return transactionRepository.findById(id).orElse(null);
  }

  public void save(Transaction transaction) {
      transactionRepository.save(transaction);
  }
}
