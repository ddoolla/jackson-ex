package com.example.jacksonex.annotation.inclusion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화 시, 어떤 값을 포함할지 설정
 */
public class JsonIncludeEx {

    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Person {

        private String name;
        private Integer age;
        private String hobby;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", null, "");

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
