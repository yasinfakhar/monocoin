package ir.monocoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication

public class MonocoinApplication {

    public static void main(String[] args) {

        SpringApplication.run(MonocoinApplication.class, args);
        System.out.println("here");
    }


}
