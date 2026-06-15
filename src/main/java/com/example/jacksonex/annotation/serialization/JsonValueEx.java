package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화에 사용될 단일 값 지정할 때 사용
 * 주로 열거형에서 많이 사용됨
 */
public class JsonValueEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        private String name;
        private Integer age;
        private String hobby;
        private Gender gender;
    }

    @Getter
    private enum Gender {
        MALE("남자"),
        FEMALE("여자");

        @JsonValue
        private final String description; // MALE, FEMALE 이 아닌, "남자", "여자" 값으로 직렬화

        Gender(String description) {
            this.description = description;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", 33, "movie",  Gender.MALE);

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
