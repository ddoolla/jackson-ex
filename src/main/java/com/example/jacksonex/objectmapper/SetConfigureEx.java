package com.example.jacksonex.objectmapper;

import com.example.jacksonex.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SetConfigureEx {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        // gender - Person에 없는 필드!
        String jsonString = """
                {
                    "name": "Lee",
                    "age": 33,
                    "hobby": "movie",
                    "gender": "male" 
                }
                """;

        try {
            Person person = objectMapper.readValue(jsonString, Person.class);
            System.out.println("person = " + person);

        } catch (JsonProcessingException e) {
            System.out.println("파싱 에러가 발생합니다.");
        }

        // Person에 없는 필드 무시하도록 설정 (다른 옵션도 많음)
        // ex) FAIL_ON_NULL_FOR_PRIMITIVES - 기본 값 null 허용 여부
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Person person = objectMapper.readValue(jsonString, Person.class);
        System.out.println("person = " + person);

        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode jsonNodeGender = jsonNodeRoot.get("gender");

        String gender = jsonNodeGender.asText();
        System.out.println("gender = " + gender);
    }
}
