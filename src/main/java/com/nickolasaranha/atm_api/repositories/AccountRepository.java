package com.nickolasaranha.atm_api.repositories;

import com.nickolasaranha.atm_api.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    public Optional<Account> findByNumberAccount(String numberAccount);

    public boolean existsByNumberAccount(String numberAccount);
}
