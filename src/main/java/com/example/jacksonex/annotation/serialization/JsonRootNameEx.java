package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화 시, 루트 이름을 추가한다.
 * 설정도 추가해야지 작동한다. (역직렬화도 마찬가지)
 *
 * 데이터 형식을 맞출 때 사용하는 것 같은데, DTO를 한 번 더 감싸는 방법을 더 많이 사용하지 않을까?
 */
public class JsonRootNameEx {

    @JsonRootName("person")
    @Getter
    @AllArgsConstructor
    public static class Person {

        private String name;
        private Integer age;
        private String hobby;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", 33, "movie");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE); // 설정 추가

        String personAsString = objectMapper.writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
