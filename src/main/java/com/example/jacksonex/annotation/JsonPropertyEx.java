package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSON 속성 이름을 정의한다.
 *
 * 사용 전
 * {
 *  "name": "Lee",
 *  "age": 33,
 *  "hobby": "movie"
 * }
 *
 * 사용 후
 * {
 *  "user_name": "Lee",
 *  "user_age": 33,
 *  "user_hobby": "movie"
 * }
 */
public class JsonPropertyEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        @JsonProperty("user_name")
        private String name;

        @JsonProperty("user_age")
        private Integer age;

        @JsonProperty("user_hobby")
        private String hobby;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", 33, "movie");

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
