package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 직렬화 시, Map 필드의 요소를 JSON 속성으로 나열함
 */
public class JsonAnyGetterEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        private String name;

        @JsonAnyGetter
        private Map<String, String> properties;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Map<String, String> properties = new HashMap<>();
        properties.put("age", "33");
        properties.put("hobby", "movie");

        Person person = new Person("Lee", properties);

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
