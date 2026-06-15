package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 주로 날짜/시간 형식을 지정할 때 사용
 *
 * 사용 전
 * {
 *  "name": "Lee",
 *  "birthday": [1990,1,1]
 * }
 *
 * 사용 후
 * {
 *  "name": "Lee",
 *  "birthday": "1990-01-01"
 * }
 */
public class JsonFormatEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd") // [1990,1,1] -> 1990-01-01
        private LocalDate birthday;
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", LocalDate.of(1990, 1, 1));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // java time 패키지를 사용하기 위해서는 모듈을 등록해야 한다.

        String personAsString = objectMapper.writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
