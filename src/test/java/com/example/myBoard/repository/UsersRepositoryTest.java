package com.example.myBoard.repository;

import com.example.myBoard.constant.Gender;
import com.example.myBoard.entity.Users;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    // 문제 1. 여성의 이름 중 "w"또는 "m"을 포함하는 자료를 검색하시오.
    @Test
    void 문제1(){
        usersRepository.findByGenderAndNameContainsOrGenderAndNameContains(Gender.Female, "w",Gender.Female,"m").forEach(users -> System.out.println(users));
    }

    @Test
    void 문제1풀이(){
        List<Users> usersList = usersRepository.findByNameLikeOrNameLike("%w%", "%m%");
        for(Users user : usersList){
            if(user.getGender().equals(Gender.Female)){
                System.out.println(user);
            }
        }
    }

    // 문제 2. 이메일에 net을 포함하는 데이터 건수를 출력하시오.
    @Test
    void 문제2(){
        Long count = usersRepository.findByEmailContains("net").stream().count();
        System.out.println("데이터 건수 : " + count);
    }

    // 문제 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 "J"인 자료를 출력하시오.
    @Test
    void 문제3(){
        usersRepository.findByUpdatedAtAfterAndNameStartingWith(LocalDateTime.now().minusDays(31L),"J")
                .forEach(users -> System.out.println(users));
    }
    @Test
    void 문제3풀이(){
        usersRepository.findByUpdatedAtGreaterThanEqualAndNameLike(LocalDateTime.now().minusMonths(1),"J%")
                .forEach(users -> System.out.println(users));
    }

    // 문제 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일만 출력하시오.
    @Test
    void 문제4(){
       List<Users> usersList = usersRepository.findTop10ByOrderByCreatedAtDesc();
       for(Users users : usersList){
           System.out.print(users.getId());
           System.out.print(", " + users.getName());
           System.out.print(", " + users.getGender());
           System.out.print(", " + users.getCreatedAt());
       }
    }

    // 문제 5. "Red"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력하시오.
    //(예, apenley2@tripod.com  → apenley2)
    @Test
    void 문제5(){
        String color = "Red";
        List<Users> userList = usersRepository.findByLikeColorAndGender(color, Gender.Male).stream().toList();
        for(Users users : userList){
            String[] email = users.getEmail().split("@");
            String emailId = email[0];
            System.out.println(emailId);
        }
    }
    @Test
    void 문제5풀이(){
        String color = "Red";
        List<Users> userList = usersRepository.findByLikeColorAndGender(color, Gender.Male).stream().toList();
        for(Users users : userList){
            String email = users.getEmail();
            String emailId = email.substring(0, email.indexOf("@"));
            System.out.println(email + ", emailId = " + emailId);
        }
    }

    // 문제 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.
    @Test
    void 문제6(){
        List<Users> usersList = usersRepository.findAll();
        for(Users users : usersList){
            LocalDateTime createdAt = users.getCreatedAt();
            LocalDateTime updatedAt = users.getUpdatedAt();
            if(createdAt.isAfter(updatedAt)){ //isAfter/isBefore
                System.out.println(users);
            }
        }

    }
    // 문제 7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.
    @Test
    void 문제7(){
        usersRepository.findByEmailContainsAndGenderOrderByCreatedAtDesc("edu", Gender.Female)
                .forEach(users -> System.out.println(users));
    }
    // 문제 8. 좋아하는 색상별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력하시오.
    @Test
    void 문제8(){
        usersRepository.findAll(
                Sort.by(Sort.Order.asc("likeColor"),
                        Sort.Order.desc("name")))
                .forEach(users -> System.out.println(users));
    }
    // 문제 9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징 처리하고
    //한 페이지당 10건씩 출력하되, 그 중 1번째 페이지를 출력하시오.
    @Test
    void 문제9(){
        usersRepository.findAll(
                        PageRequest.of(0, 10,
                                Sort.by(Sort.Order.desc("id"))))
                .getContent().forEach(users -> System.out.println(users));
    }

    // 문제10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되
    //그 중 2번째 페이지 자료를  출력하시오.
    @Test
    void 문제10(){
        Pageable pageable = PageRequest.of(1, 3);
        Page<Users> result = usersRepository.findByGenderOrderByIdDesc(
                Gender.Male, pageable);
        result.getContent().forEach(users -> System.out.println(users));
    }
    // 문제11. 지난달의 모든 자료를 검색하여 출력하시오.
    @Test
    void 문제11(){
        usersRepository.findByCreatedAtBetween
                (LocalDateTime.now().minusMonths(1).withDayOfMonth(1),
                        LocalDateTime.now().minusMonths(1)
                                .with(TemporalAdjusters.lastDayOfMonth()))
                .forEach(users -> System.out.println(users));
    }

    @Test
    void 문제11풀이(){
        LocalDate baseDate = LocalDate.now().minusMonths(1L);
        LocalDate startDate = baseDate.withDayOfMonth(1);
        LocalDate lastDate = baseDate.withDayOfMonth(baseDate.lengthOfMonth());
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime lastDateTime = lastDate.atStartOfDay();
        usersRepository.findByCreatedAtBetween(startDateTime, lastDateTime).forEach(users -> System.out.println(users));
    }


    @Test
    void findByName테스트(){
        String findName = "Nikos";
        usersRepository.findByName(findName)
                .forEach(users -> System.out.println(users));
    }

    @Test
    void findTop3ByLikeColor테스트(){
        String color = "Pink";
        usersRepository.findTop3ByLikeColor(color)
                .forEach(users -> System.out.println(users));
    }

    @Test
    void findByGenderAndLikeColor테스트(){
        String color = "Pink";
        usersRepository.findByGenderAndLikeColor(Gender.Male, color)
                .forEach(users -> System.out.println(users));
    }

    @Test
    void findByCreatedAtAfter테스트(){
        usersRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(10L))
                .forEach(users -> System.out.println(users));
    }

    @Test
    void findByColorIn테스트(){
        usersRepository.findByLikeColorIn(Lists.newArrayList("Red", "Orange"))
                .forEach(users -> System.out.println(users));

    }

    @Test
    void findByColorAndSort(){
        usersRepository.findByLikeColor("Orange",
                Sort.by(Sort.Order.asc("gender"),
                        Sort.Order.desc("createdAt")))
                .forEach(users -> System.out.println(users));
    }

    // 전체 페이징
    @Test
    void pagingTest(){
        System.out.println("페이지 : 0, 페이지당 리스트 수 : 5" );
        usersRepository.findAll(
                PageRequest.of(0, 5,
                        Sort.by(Sort.Order.desc("id"))))
                .getContent().forEach(users -> System.out.println(users));

        System.out.println("페이지 : 1, 페이지당 리스트 수 : 5" );
        usersRepository.findAll(
                        PageRequest.of(1, 5,
                                Sort.by(Sort.Order.desc("id"))))
                .getContent().forEach(users -> System.out.println(users));

        System.out.println("페이지 : 2, 페이지당 리스트 수 : 5" );
        usersRepository.findAll(
                        PageRequest.of(2, 5,
                                Sort.by(Sort.Order.desc("id"))))
                .getContent().forEach(users -> System.out.println(users));
    }
    // (id > 200, 5번째 페이지, 한페이지당 10개씩)
    @Test
    void pagingTest2(){
        Pageable pageable = PageRequest.of(4, 10);
        Page<Users> result = usersRepository.findByIdGreaterThanEqualOrderByIdDesc(
                200L, pageable);
        result.getContent().forEach(users -> System.out.println(users));
        // 총 페이지 수
        System.out.println("Total Pages :" + result.getTotalPages());
        // 전체 데이터 개수
        System.out.println("Total Contents Count : " + result.getTotalElements());
        // 현재 페이지의 번호
        System.out.println("Current Page Number : " + result.getNumber());
        // 다음 페이지 존재 여부
        System.out.println("Next Page? : " + result.hasNext());
        // 시작 페이지 여부
        System.out.println("Is First Page? : " + result.isFirst());
    }


}