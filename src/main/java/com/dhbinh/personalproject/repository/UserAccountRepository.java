package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String userName);

    Optional<UserAccount> findByLastName(String lastname);

    Boolean existsByUsername(String username);
}
