package com.example.myBoard.entity;

import com.example.myBoard.constant.UserRole;
import com.nimbusds.oauth2.sdk.TokenIntrospectionSuccessResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @Column(length = 50)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 100)
    private String email;
    @Column(name="nickname", length = 50)
    private String nickname;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    //Oauth2 데이터 저장용
    private String provider;
    private String providerId;


}
