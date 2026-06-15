package com.example.jacksonex.objectmapper;

import com.example.jacksonex.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class BasicEx {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Person person = new Person("Lee", 33, "movie");

        // object to JSON
        String personAsString = objectMapper.writeValueAsString(person);
        System.out.println("personAsString = " + personAsString);

        // JSON to object
        String json = """
                {
                    "name": "Moon",
                    "age": 33,
                    "hobby": "shorts"
                }
                """;

        Person personObject = objectMapper.readValue(json, Person.class);
        System.out.println("personObject = " + personObject);

        JsonNode jsonNode = objectMapper.readTree(json);
        String name = jsonNode.get("name").asText();
        System.out.println("name = " + name);

        // list from JSON array
        String jsonPersonArray = """
                [
                    {"name": "A", "age": 1, "hobby": "TV"},
                    {"name": "B", "age": 2, "hobby": "GAME"},
                    {"name": "C", "age": 3, "hobby": "WORKOUT"}
                ]
                """;

        List<Person> people = objectMapper.readValue(jsonPersonArray, new TypeReference<List<Person>>() {});
        System.out.println("people = " + people);

        // map from JSON
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        System.out.println("map = " + map);
    }
}
