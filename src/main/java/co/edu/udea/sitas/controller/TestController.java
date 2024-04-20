package co.edu.udea.sitas.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class TestController {

    @GetMapping(produces = "text/html")
    public ResponseEntity<String> sayHola(){
        return ResponseEntity.ok("Hola mundo");
    }
}
