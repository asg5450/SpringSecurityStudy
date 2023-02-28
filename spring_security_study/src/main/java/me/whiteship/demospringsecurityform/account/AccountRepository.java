package me.whiteship.demospringsecurityform.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
