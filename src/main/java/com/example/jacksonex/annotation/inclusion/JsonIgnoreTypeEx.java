package com.example.jacksonex.annotation.inclusion;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직렬화, 역직렬화 시 타입 자체를 무시
 */
public class JsonIgnoreTypeEx {

    @Getter
    @AllArgsConstructor
    private static class User {

        private String name;
        private Address address;
    }

    @Getter
    @AllArgsConstructor
    @JsonIgnoreType
    private static class Address {

        private String city;
        private String road;
    }

    public static void main(String[] args) throws JsonProcessingException {

        User user = new User("Lee", new Address("부천", "삼작로"));

        String userAsString = new ObjectMapper().writeValueAsString(user);

        System.out.println("userAsString = " + userAsString);
    }
}
