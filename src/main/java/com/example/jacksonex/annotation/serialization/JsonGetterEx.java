package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화 시, 특정 게터 메서드를 JSON 속성에 추가함
 */
public class JsonGetterEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        private String firstName;
        private String lastName;

        @JsonGetter
        public String getFullName() {
            return this.firstName + " " + this.lastName;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", "JH");

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
