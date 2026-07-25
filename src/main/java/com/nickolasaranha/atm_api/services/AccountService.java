package com.nickolasaranha.atm_api.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.nickolasaranha.atm_api.entities.Account;
import com.nickolasaranha.atm_api.entities.Transaction;
import com.nickolasaranha.atm_api.entities.enums.TransactionEnum;
import com.nickolasaranha.atm_api.repositories.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Random;

@Service
public class AccountService {
    private AccountRepository repository;
    private final TransactionService service;

    public AccountService(AccountRepository accountRepository, TransactionService transactionService) {
        this.repository = accountRepository;
        this.service = transactionService;
    }

    public Account save(Account account) {
        Random random = new Random();
        String accountNumber;
        do {
            int randomNum = 100000 + random.nextInt(900000); // Gera entre 100000 e 999999
            accountNumber = String.valueOf(randomNum);
        } while (repository.existsByNumberAccount(accountNumber));
        account.setNumberAccount(accountNumber);
        account.setPassword(BCrypt.withDefaults().hashToString(10, account.getPassword().toCharArray()));
        return repository.save(account);
    }

    public Account findByNumberAccount(String numberAccount) {
        return repository.findByNumberAccount(numberAccount).orElse(null);
    }

    public Account withdraw(String NumberAccount, BigDecimal amount) {
        if (repository.existsByNumberAccount(NumberAccount)) {
            Account account = repository.findByNumberAccount(NumberAccount).orElse(null);
            if (account.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            account.setBalance(account.getBalance().subtract(amount));
            Transaction t = new Transaction(null, TransactionEnum.WITHDRAW, Instant.now(), account, amount);
            service.save(t);
            account.getTransactions().add(t);
            return repository.save(account);
        }
        return null;
    }

    public Account deposit(String numberAccount, @Valid BigDecimal amount) {
        if (repository.existsByNumberAccount(numberAccount)) {
            Account account = repository.findByNumberAccount(numberAccount).orElse(null);
            account.setBalance(account.getBalance().add(amount));
            Transaction t = new Transaction(null, TransactionEnum.DEPOSIT, Instant.now(), account, amount);
            service.save(t);
            account.getTransactions().add(t);
            return repository.save(account);
        } return null;
    }
}
