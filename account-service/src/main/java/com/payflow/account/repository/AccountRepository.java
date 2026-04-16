package com.payflow.account.repository;

import com.payflow.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

   Optional<Account> findByUserId(Long id);
}
