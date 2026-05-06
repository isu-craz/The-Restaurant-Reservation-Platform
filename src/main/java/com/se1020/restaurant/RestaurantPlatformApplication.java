package com.se1020.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantPlatformApplication.class, args);
        System.out.println("open the web page via:    http://localhost:8080/");
    }

}
