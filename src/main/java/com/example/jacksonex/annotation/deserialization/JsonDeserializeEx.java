package com.example.jacksonex.annotation.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 사용자 지정 역직렬화기 사용
 *
 * 날짜 포멧의 경우 JsonFormat, Enum의 경우 JsonCreator 활용을 검토하자.
 */
public class JsonDeserializeEx {

    @Getter
    @ToString
    @AllArgsConstructor
    @JsonDeserialize(using = UserDeserializer.class)
    private static class User {

        private String name;
        private Gender gender;
        private Boolean isActive;
        private LocalDate joinedAt;
    }

    private enum Gender {
        MALE,
        FEMALE
    }

    private static class UserDeserializer extends JsonDeserializer<User> {

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        @Override
        public User deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {

            JsonNode node = parser.getCodec().readTree(parser);

            String name = node.get("user_name").asText();

            Gender gender = switch (node.get("gender_code").asText()) {
                case "M" -> Gender.MALE;
                case "F" -> Gender.FEMALE;
                default -> throw new IllegalArgumentException();
            };

            boolean active = "Y".equals(node.get("is_active").asText());

            LocalDate joinedAt = LocalDate.parse(node.get("joined_at").asText(), FORMATTER);

            return new User(name, gender, active, joinedAt);
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        String json = """
                {
                    "user_name": "Lee",
                    "gender_code": "M",
                    "is_active": "Y",
                    "joined_at": "2026년 06월 17일"
                }
                """;

        User user = new ObjectMapper().readValue(json, User.class);

        System.out.println("user = " + user);
    }
}
