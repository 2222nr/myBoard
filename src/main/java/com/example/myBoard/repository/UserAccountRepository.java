package com.example.myBoard.repository;

import com.example.myBoard.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByProviderAndProviderId(String provider, String providerId);
}
