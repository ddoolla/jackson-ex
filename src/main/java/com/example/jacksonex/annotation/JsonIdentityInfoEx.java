package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 객체의 식별자(ID)를 이용해서 순환 참조를 해결하기 위해 사용
 *
 * 보통 DTO를 따로 만들어서 사용하기 때문에 사용할 일이 별로 없을 것 같다.
 */
public class JsonIdentityInfoEx {

    @Getter
    @AllArgsConstructor
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    private static class Member {

        private Long id;
        private String name;
        private Team team;
    }

    @Getter
    @AllArgsConstructor
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    private static class Team {

        private Long id;
        private String name;
        private List<Member> members;
    }

    public static void main(String[] args) throws JsonProcessingException {

        List<Member> members = new ArrayList<>();

        Team teamA = new Team(1L, "A", members);
        Member lee = new Member(1L, "Lee", teamA);
        Member kim = new Member(2L, "Kim", teamA);

        members.add(lee);
        members.add(kim);

        ObjectMapper objectMapper = new ObjectMapper();

        String memberAsString = objectMapper.writeValueAsString(lee);
        String teamAsString = objectMapper.writeValueAsString(teamA);

        System.out.println("memberAsString = " + memberAsString);
        System.out.println("teamAsString = " + teamAsString);
    }
}
