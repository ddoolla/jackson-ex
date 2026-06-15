package com.example.jacksonex.annotation.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 사용자 정의 직렬화기 만들 때 사용
 */
public class JsonSerializeEx {

    @Getter
    @AllArgsConstructor
    private static class Person {

        private String name;

        @JsonSerialize(using = BirthDaySerializer.class) // yyyy-MM-dd -> yyyy/MM/dd
        private LocalDate birthday;
    }

    private static class BirthDaySerializer extends JsonSerializer<LocalDate> {

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

            String formatted = value.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            gen.writeString(formatted);
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", LocalDate.of(2000, 1, 1));

        String personAsString = new ObjectMapper().writeValueAsString(person);

        System.out.println("personAsString = " + personAsString);
    }
}
