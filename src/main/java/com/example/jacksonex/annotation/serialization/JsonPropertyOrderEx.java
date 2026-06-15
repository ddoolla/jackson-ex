package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화 시, 속성 순서를 지정함.
 * alphabetic 옵션을 사용하면 알파벳순 정렬 가능
 */
public class JsonPropertyOrderEx {

    @JsonPropertyOrder({"hobby", "name", "age"})
//    @JsonPropertyOrder(alphabetic = true)
    @Getter
    @AllArgsConstructor
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
