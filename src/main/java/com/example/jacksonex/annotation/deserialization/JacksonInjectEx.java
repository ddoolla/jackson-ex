package com.example.jacksonex.annotation.deserialization;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

/**
 * JSON 데이터 역직렬화할 떄, 없는 속성 값 주입
 */
public class JacksonInjectEx {

    @Getter
    @ToString
    private static class Person {

        private String name;

        @JacksonInject
        private Integer age;
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json = """
                {
                    "name": "Lee"
                }
                """;

        InjectableValues injectAge = new InjectableValues.Std()
                .addValue(Integer.class, 33);

        Person person = new ObjectMapper().reader(injectAge)
                .forType(Person.class)
                .readValue(json);

        System.out.println("person = " + person);
    }
}
