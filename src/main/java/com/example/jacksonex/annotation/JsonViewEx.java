package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화, 역직렬화 시 해당 속성이 포할될 뷰를 지정
 *
 * 사용 전 (뷰 지정 X)
 * {
 *  "id": 1,
 *  "username": "user1",
 *  "password": "1234"
 * }
 *
 * 사용 후 (Public 뷰 지정)
 * {
 *  "id": 1,
 *  "username": "user1"
 * }
 */
public class JsonViewEx {

    private static class Views {

        public interface Public {
        }

        public interface Internal extends Public {
        }
    }

    @Getter
    @AllArgsConstructor
    private static class User {

        @JsonView(Views.Public.class)
        private Long id;

        @JsonView(Views.Public.class)
        private String username;

        @JsonView(Views.Internal.class)
        private String password;
    }

    public static void main(String[] args) throws JsonProcessingException {

        User user = new User(1L, "user1", "1234");

        String userAsString = new ObjectMapper()
                .writerWithView(Views.Public.class)
                .writeValueAsString(user);

        System.out.println("userAsString = " + userAsString);
    }
}
