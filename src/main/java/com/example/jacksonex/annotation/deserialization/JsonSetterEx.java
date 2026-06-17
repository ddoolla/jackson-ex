package com.example.jacksonex.annotation.deserialization;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

/**
 * 역직렬화 시, 속성과 필드명이 불일치할 때 사용.
 */
public class JsonSetterEx {

    @Getter
    @ToString
    private static class Person {

        @JsonSetter("full_name")
        private String name;
        private Integer age;
        private String hobby;
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json = """
                {
                    "full_name": "Lee JH",
                    "age": 33,
                    "hobby": "movie"
                }
                """;

        Person person = new ObjectMapper().readValue(json, Person.class);

        System.out.println("person = " + person);
    }
}
