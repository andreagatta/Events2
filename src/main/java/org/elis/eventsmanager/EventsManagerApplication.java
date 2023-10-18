package org.elis.eventsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class EventsManagerApplication {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().toString());
        SpringApplication.run(EventsManagerApplication.class, args);
    }

}
