package com.example.jacksonex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @PostMapping("/test")
    public ResponseEntity<Void> deserialization_test(@RequestBody UserDto dto) {
        System.out.println("dto = " + dto);
        return ResponseEntity.ok().build();
    }
}
