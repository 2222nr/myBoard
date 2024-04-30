package com.example.myBoard.repository;

import com.example.myBoard.constant.Gender;
import com.example.myBoard.entity.Users;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("Users_테이블_입력_테스트")
    void inputUsers(){
        Users users = Users.builder()
                .name("홍길동")
                .email("test@test.com")
                .gender(Gender.Male)
                .likeColor("Red")
                .build();
        usersRepository.save(users);
    }

    @Test
    @DisplayName("Users_테이블_수정_테스트")
    public void updateUsers(){
        Users users = Users.builder()
                .id(501L)
                .name("홍길순")
                .email("test@test.com")
                .gender(Gender.Female)
                .likeColor("Yellow")
                .build();
        usersRepository.save(users);
    }
}