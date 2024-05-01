package com.example.myBoard.repository;

import com.example.myBoard.constant.Gender;
import com.example.myBoard.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // 이름으로 검색
    List<Users> findByName(String name);

    // pink 색상 데이터 중 상위 3개 데이터만 가져오기
    List<Users> findTop3ByLikeColor(String color);

    // Gender와 Color로 테이블 검색
    List<Users> findByGenderAndLikeColor(Gender gender, String color);

    // 범위로 검색(After, Before) -- 날짜/시간 데이터에 한정
    List<Users> findByCreatedAtAfter(LocalDateTime searchDate);

    List<Users> findByLikeColorIn(List<String> color);

    // sort 별도로 처리하는 방법
    // Orange 색상 검색해서 Gender 오름차순, CreateAt 내림차순
    List<Users> findByLikeColor(String color, Sort sort);

    // 페이징 처리
    // 주어진 아이디보다 큰 데이터를 내림차순으로 검색 후 페이징 처리
    // (id > 200, 5번째 페이지, 한페이지당 10개씩)
    Page<Users> findByIdGreaterThanEqualOrderByIdDesc(Long id, Pageable paging);


    List<Users> findByGenderAndNameContainsOrGenderAndNameContains(Gender gender1, String name1, Gender gender2, String name2);
//    List<Users> findByNameContainsAndNameContainsOrGender(String name1, String name2, Gender gender2);
    // 문제1 풀이
    List<Users> findByNameLikeOrNameLike(String str1, String str2);
    List<Users> findByEmailContains(String email);

    List<Users> findByUpdatedAtAfterAndNameStartingWith(LocalDateTime searchDate, String name);
    // 문제 3 풀이
    List<Users> findByUpdatedAtGreaterThanEqualAndNameLike(LocalDateTime searchDate, String name);

    List<Users> findTop10ByOrderByCreatedAtDesc();
    List<Users> findByLikeColorAndGender(String color, Gender gender);

    List<Users> findByEmailContainsAndGenderOrderByCreatedAtDesc(String email, Gender gender);

    Page<Users> findByGenderOrderByIdDesc(Gender gender, Pageable paging);

    List<Users> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);





}
