package com.example.myBoard.dto;

import com.example.myBoard.constant.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateForm {
    @Size(min=3, max=15)
    @NotEmpty(message = "사용자 ID는 필수 입니다.")
    private String userId;
    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String userPassword1;
    @NotEmpty(message = "비밀번호 확인은 필수 입니다.")
    private String userPassword2;
    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;
    private String nickname;
}
