package com.example.jacksonex.annotation.inclusion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 직렬화, 역직렬화 시 속성 무시 (필드 레벨)
 */
public class JsonIgnoreEx {

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {

        private String username;

        @JsonIgnore
        private String password;
    }

    public static void main(String[] args) throws JsonProcessingException {

        // 직렬화
        User admin = new User("admin", "1234");

        String adminAsString = new ObjectMapper().writeValueAsString(admin);

        System.out.println("adminAsString = " + adminAsString);

        // 역직렬화
        String json = """
                {
                    "username": "tester",
                    "password": "1234"
                }
                """;

        User user = new ObjectMapper().readValue(json, User.class);

        System.out.println("user = " + user);
    }
}
