package com.example.jacksonex.annotation.inclusion;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화/역직렬화 시, 포함할 필드 설정
 */
public class JsonIncludePropertiesEx {

    @Getter
    @AllArgsConstructor
    @JsonIncludeProperties({"name", "hobby"})
    private static class Person {

        private String name;
        private Integer age;
        private String hobby;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", 33, "movie");

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
