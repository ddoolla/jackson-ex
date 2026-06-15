package com.example.jacksonex.annotation.serialization;

import com.example.jacksonex.objectmapper.Person;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSON 형태의 문자열 값을 그대로 직렬화할 때 사용한다.
 */
public class JsonRawValueEx {

    @Getter
    @AllArgsConstructor
    private static class Foo {

        private String foo;

        @JsonRawValue
        private String json;
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json = """
                {
                    "person": {
                        "name": "Lee",
                        "age": 33,
                        "hobby": "movie"
                    }
                }
                """;

        Foo foo = new Foo("Hello", json);

        ObjectMapper objectMapper = new ObjectMapper();

        String fooAsString = objectMapper.writeValueAsString(foo);

        System.out.println("fooAsString = " + fooAsString);

        // JSON to Person
        JsonNode jsonNodeRoot = objectMapper.readTree(fooAsString);
        JsonNode jsonNodePerson = jsonNodeRoot.get("json").get("person");

        Person person = objectMapper.treeToValue(jsonNodePerson, Person.class);

        System.out.println("person = " + person);
    }
}
