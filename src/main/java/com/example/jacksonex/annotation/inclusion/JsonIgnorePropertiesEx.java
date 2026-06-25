package com.example.jacksonex.annotation.inclusion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 직렬화, 역직렬화 시 무시할 속성들 정의 (클래스 레벨)
 * DTO를 상황에 맞게 분리해서 사용하면 쓸 일은 거의 없을 듯
 *
 * ignoreUnknown = true
 * 역직렬화 시, JSON에 DTO에 없는 필드가 들어와도 예외 발생 없이 무시
 * 외부 API 호출 시 필요한 값만 역직렬화 할 때 사용
 */
public class JsonIgnorePropertiesEx {

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties({"id", "password"})
    private static class User {

        private Long id;
        private String username;
        private String password;
    }

    public static void main(String[] args) throws JsonProcessingException {

        // 직렬화
        User Lee = new User(1L, "Lee", "1234");

        String userAsString = new ObjectMapper().writeValueAsString(Lee);

        System.out.println("userAsString = " + userAsString);

        // 역직렬화
        String json = """
                {
                    "id": 2,
                    "username": "Kim",
                    "password": "3456"
                }
                """;

        User Kim = new ObjectMapper().readValue(json, User.class);

        System.out.println("Kim = " + Kim);
    }
}
