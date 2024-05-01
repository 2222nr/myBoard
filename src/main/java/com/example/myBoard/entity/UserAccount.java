package com.example.myBoard.entity;

import com.example.myBoard.constant.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserAccount {
    @Id
    @Column(name="user_name", length = 50)
    private String userId;
    @Column(nullable = false)
    private String userPassword;
    @Column(length = 100)
    private String email;
    @Column(name="nickname", length = 50)
    private String nickname;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
