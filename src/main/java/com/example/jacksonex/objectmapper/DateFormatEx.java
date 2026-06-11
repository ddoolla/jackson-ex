package com.example.jacksonex.objectmapper;

import com.example.jacksonex.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatEx {

    public record Request(Person person, Date date) {
    }

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Lee", 33, "movie");
        Request request = new Request(person, new Date());

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        objectMapper.setDateFormat(sdf);

        String requestAsString = objectMapper.writeValueAsString(request);
        System.out.println("requestAsString = " + requestAsString);
    }
}
