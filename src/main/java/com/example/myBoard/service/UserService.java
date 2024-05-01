package com.example.myBoard.service;

import com.example.myBoard.constant.UserRole;
import com.example.myBoard.dto.UserCreateForm;
import com.example.myBoard.entity.UserAccount;
import com.example.myBoard.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EntityManager em;
    @Transactional
    public void createUser(UserCreateForm userCreateForm) {
        UserAccount account = new UserAccount();
        account.setUserId(userCreateForm.getUserId());
        account.setUserPassword(passwordEncoder.encode(userCreateForm.getUserPassword1()));
        account.setEmail(userCreateForm.getEmail());
        account.setNickname(userCreateForm.getNickname());
        if("ADMIN".equals(userCreateForm.getUserId().toUpperCase())){
            account.setUserRole(UserRole.ADMIN);
        } else {
            account.setUserRole(UserRole.USER);
        }
        em.persist(account);
    }
}
