package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화 시, 특정 게터 메서드를 JSON 속성에 추가함
 *
 * 사용하지 않아도 게터 메서드 형식에 맞으면 JSON 프로퍼티로 인식함
 * 이름을 바꾸기 위해서 사용한다면 차라리 @JsonProperty를 사용하면 될 듯?
 */
public class JsonGetterEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        private String firstName;
        private String lastName;

        @JsonGetter("full_name")
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
