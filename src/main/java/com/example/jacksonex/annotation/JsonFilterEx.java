package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화 과정에서 사용할 필터를 지정
 */
public class JsonFilterEx {

    @Getter
    @AllArgsConstructor
    @JsonFilter("personFilter")
    public static class Person {

        private String name;
        private Integer age;
        private String hobby;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", 33, "movie");

        FilterProvider filters = new SimpleFilterProvider().addFilter(
                "personFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name", "age")
        );

        String personAsString = new ObjectMapper()
                .writer(filters)
                .writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
