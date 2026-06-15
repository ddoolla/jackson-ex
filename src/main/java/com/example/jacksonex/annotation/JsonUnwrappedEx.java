package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 중첩 객체 필드를 펼쳐서(flatten) 직렬화/역직렬화 한다.
 *
 * 사용 전
 * {
 *  "name": {
 *      "firstName": "Lee",
 *      "lastName": "JH"
 *  },
 *  "age": 33,
 *  "hobby": "movie"
 * }
 *
 * 사용 후
 * {
 *  "firstName": "Lee",
 *  "lastName": "JH",
 *  "age": 33,
 *  "hobby": "movie"
 * }
 */
public class JsonUnwrappedEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        @JsonUnwrapped
        private Name name;
        private Integer age;
        private String hobby;

        @Getter
        @AllArgsConstructor
        private static class Name {

            private String firstName;
            private String lastName;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person(new Person.Name("Lee", "JH"), 33, "movie");

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
