package com.example.myBoard.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE ADMIN"),
    USER("ROLE USER");
    private String value;

    UserRole(String value) {
        this.value = value;
    }
}
