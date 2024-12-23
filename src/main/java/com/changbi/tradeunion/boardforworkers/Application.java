package com.changbi.tradeunion.boardforworkers;

import com.changbi.tradeunion.boardforworkers.application.service.ApplicationMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
