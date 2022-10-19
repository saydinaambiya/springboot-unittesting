package com.enigmacamp.hellospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("console")
public class Console implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Hello Spring Boot");
    }
}
