package com.example.myBoard.entity;

import com.example.myBoard.constant.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)    // 기본: 숫자타입
    private Gender gender;
    @Column(name="like_color")
    private String likeColor;
    @Column(name="created_at", updatable = false)
    //CreateBy => 누가 작성했는지아아아아
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name="updated_at", insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
