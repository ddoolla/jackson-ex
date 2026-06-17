package com.example.jacksonex.annotation.deserialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

/**
 * 생성자 또는 정적 팩토리 메서드를 사용해서 역직렬화
 *
 * 생성자를 사용한 불변 객체를 만들 때 사용
 * JSON 속성이 일치하지 않을 때 사용
 */
public class JsonCreatorEx {

    @Getter
    @ToString
    private static class Person {

        private final String name;
        private final Integer age;
        private final String hobby;

        @JsonCreator
        public Person(@JsonProperty("full_name") String name,
                      @JsonProperty("age") Integer age,
                      @JsonProperty("hobby") String hobby) {
            this.name = name;
            this.age = age;
            this.hobby = hobby;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json = """
                {
                    "full_name": "Lee",
                    "age": 33,
                    "hobby": "movie"
                }
                """;

        Person person = new ObjectMapper().readValue(json, Person.class);

        System.out.println("person = " + person);
    }
}
