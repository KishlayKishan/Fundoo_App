package com.bridgelabz.fundooeurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class FundooEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundooEurekaServerApplication.class, args);
        log.info("Fundoo Eureka Server Started");
    }

}
