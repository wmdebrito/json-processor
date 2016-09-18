package com.wmdebrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStarter {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args).close();
        System.out.println("Execution completed successfully!");
    }

}
