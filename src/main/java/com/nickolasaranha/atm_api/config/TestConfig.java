package com.nickolasaranha.atm_api.config;

import com.nickolasaranha.atm_api.entities.Account;
import com.nickolasaranha.atm_api.entities.Transaction;
import com.nickolasaranha.atm_api.entities.enums.TransactionEnum;
import com.nickolasaranha.atm_api.repositories.AccountRepository;
import com.nickolasaranha.atm_api.services.AccountService;
import com.nickolasaranha.atm_api.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private AccountService accountService;
    private TransactionService transactionService;

    public TestConfig(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account(null, 10, "100000", "oii", BigDecimal.valueOf(100.0), new ArrayList<>());
        accountService.save(account);

        Transaction transaction = new Transaction(null, TransactionEnum.WITHDRAW, Instant.parse("2019-06-20T19:53:07Z"), account, BigDecimal.valueOf(100.0));
        Transaction trans2 = new Transaction(null, TransactionEnum.DEPOSIT, Instant.parse("2019-06-20T19:53:08Z"), account, BigDecimal.valueOf(200.0));

        transactionService.save(transaction);
        transactionService.save(trans2);
    }
}
