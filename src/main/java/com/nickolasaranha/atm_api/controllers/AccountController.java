package com.nickolasaranha.atm_api.controllers;

import com.nickolasaranha.atm_api.entities.Account;
import com.nickolasaranha.atm_api.repositories.AccountRepository;
import com.nickolasaranha.atm_api.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService accountService) {
        this.service = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> save(@Valid @RequestBody Account account) {
        Account savedAccount = service.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @GetMapping("/{numberAccount}")
    public ResponseEntity<Account> findByNumberAccount(@PathVariable("numberAccount") String numberAccount) {
        Account obj = service.findByNumberAccount(numberAccount);
        if (obj != null) {
            return ResponseEntity.ok().body(obj);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{numberAccount}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable("numberAccount") String numberAccount, @Valid @RequestBody BigDecimal amount) {
        Account obj = service.withdraw(numberAccount, amount);
        if (obj != null) {
            return ResponseEntity.ok().body(obj);   
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{numberAccount}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable("numberAccount") String numberAccount, @Valid @RequestBody BigDecimal amount) {
        Account obj  = service.deposit(numberAccount, amount);
        if (obj != null) {
            return ResponseEntity.ok().body(obj);
        }
        return ResponseEntity.badRequest().build();
    }
}
