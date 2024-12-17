package com.example.crudwarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrudWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudWarehouseApplication.class, args);
    }

}
