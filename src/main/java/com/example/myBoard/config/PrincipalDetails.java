package com.example.myBoard.config;

import com.example.myBoard.entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PrincipalDetails implements UserDetails {
    private UserAccount user;

    public PrincipalDetails(UserAccount user) {
        this.user = user;
    }

    public UserAccount getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(
                ()->{return  user.getUserRole().getValue();}
        );
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠금 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호의 만료 여부 ex) 6개월 3개월 주기마다 비번 바꾸리고 하는거
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정 사용 여부 ( 계정 활성화)
        return true;
    }
}
