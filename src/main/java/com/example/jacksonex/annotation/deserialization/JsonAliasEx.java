package com.example.jacksonex.annotation.deserialization;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

/**
 * 역직렬화 시, 속성의 대체 이름을 여러 개 설정할 수 있다.
 * */
public class JsonAliasEx {

    @Getter
    @ToString
    private static class Person {

        @JsonAlias({"fullName", "full_name"})
        private String name;
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json1 = """
                {
                    "fullName": "Lee JH"
                }
                """;

        Person person1 = new ObjectMapper().readValue(json1, Person.class);

        System.out.println("person1 = " + person1);

        String json2 = """
                {
                    "full_name": "Hong GD"
                }
                """;

        Person person2 = new ObjectMapper().readValue(json2, Person.class);

        System.out.println("person2 = " + person2);
    }
}
