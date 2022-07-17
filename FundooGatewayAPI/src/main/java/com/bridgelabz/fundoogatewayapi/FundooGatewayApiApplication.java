package com.bridgelabz.fundoogatewayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FundooGatewayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundooGatewayApiApplication.class, args);
    }

}
