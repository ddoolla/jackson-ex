package com.example.jacksonex.annotation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 양방향 연관관계에서 무한 재귀를 막기위해 사용
 * 아래 예시의 경우 어노테이션이 없으면 에러가 발생한다.
 *
 * member에서 team을 출력하거나, team 에서 members를 출력하거나 둘 중 한가지 경우만 가능
 *
 * 보통 DTO를 따로 만들어서 사용하기 때문에 사용할 일이 별로 없을 것 같다.
 */
public class JsonManagedReferenceEx {

    @Getter
    @AllArgsConstructor
    private static class Member {

        private String name;

        @JsonManagedReference
        private Team team;
    }

    @Getter
    @AllArgsConstructor
    private static class Team {


        private String name;

        @JsonBackReference
        private List<Member> members;
    }

    public static void main(String[] args) throws JsonProcessingException {

        List<Member> members = new ArrayList<>();

        Team teamA = new Team("A", members);

        Member lee = new Member("Lee", teamA);
        Member kim = new Member("Kim", teamA);

        members.add(lee);
        members.add(kim);

        ObjectMapper objectMapper = new ObjectMapper();

        String memberAsString = objectMapper.writeValueAsString(lee);
        String teamAsString = objectMapper.writeValueAsString(teamA);

        System.out.println("memberAsString = " + memberAsString);
        System.out.println("teamAsString = " + teamAsString);
    }
}
