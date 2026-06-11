package com.example.jacksonex.objectmapper;

import com.example.jacksonex.Person;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializerEx {

    public static class CustomPersonSerializer extends StdSerializer<Person> {

        public CustomPersonSerializer() {
            this(null);
        }

        public CustomPersonSerializer(Class<Person> t) {
            super(t);
        }

        @Override
        public void serialize(
                Person person, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
            jsonGenerator.writeStartObject();
            // "name" 필드를 제외한 나머지 필드는 직렬화되지 않음
            jsonGenerator.writeStringField("nick_name", person.getName());
            jsonGenerator.writeEndObject();
        }

        public static void main(String[] args) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();

            // 버전 중요하지 않으면 굳이 안써도 될 듯 - new SimpleModule("CustomPersonSerializer")
            SimpleModule module = new SimpleModule("CustomPersonSerializer", new Version(1, 0, 0, null, null, null));
            module.addSerializer(Person.class, new CustomPersonSerializer());

            objectMapper.registerModule(module);

            Person person = new Person("Lee", 33, "movie");

            String personJson = objectMapper.writeValueAsString(person);
            System.out.println("personJson = " + personJson);
        }
    }
}


