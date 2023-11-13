package com.vti.shopeebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShopeeBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeBeApplication.class, args);
    }

}
