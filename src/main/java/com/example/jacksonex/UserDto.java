package com.example.jacksonex;

import lombok.Getter;
import lombok.ToString;

/**
 * 게터 또는 세터 그리고 기본 생성자 필수
 */
@Getter
@ToString
public class UserDto {

    private String username;
    private String password;
}
