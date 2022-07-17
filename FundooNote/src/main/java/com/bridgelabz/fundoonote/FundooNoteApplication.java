package com.bridgelabz.fundoonote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class FundooNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundooNoteApplication.class, args);
        log.info(" Note-Service For FundooApp Successfully Started");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
