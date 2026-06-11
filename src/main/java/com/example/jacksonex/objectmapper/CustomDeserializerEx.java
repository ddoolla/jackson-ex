package com.example.jacksonex.objectmapper;

import com.example.jacksonex.Person;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class CustomDeserializerEx {

    public static class CustomPersonDeserializer extends StdDeserializer<Person> {

        public CustomPersonDeserializer() {
            this(null);
        }

        public CustomPersonDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Person deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException, JacksonException {
            Person person = new Person();

            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);

            // "name" 필드만 역직렬화
            JsonNode nameNode = node.get("name");
            String name = nameNode.asText();

            person.setName(name);

            return person;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        String json = """
                {
                    "name": "Lee",
                    "age": 33,
                    "hobby": "movie"
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("CustomPersonDeserializer");
        module.addDeserializer(Person.class, new CustomPersonDeserializer());

        objectMapper.registerModule(module);

        Person person = objectMapper.readValue(json, Person.class);
        System.out.println("person = " + person);
    }
}
