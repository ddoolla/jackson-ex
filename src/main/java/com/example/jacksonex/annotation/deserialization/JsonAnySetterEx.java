package com.example.jacksonex.annotation.deserialization;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * 역직렬화 시, 필드에 없는 속성들을 map에 추가함
 */
public class JsonAnySetterEx {

    @Getter
    @ToString
    private static class Person {

        private String name;

        @JsonAnySetter
        private Map<String, String> properties;
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json = """
                {
                    "name": "Lee",
                    "age": "33",
                    "hobby": "movie"
                }
                """;

        Person person = new ObjectMapper().readValue(json, Person.class);

        System.out.println("person = " + person);
    }
}
