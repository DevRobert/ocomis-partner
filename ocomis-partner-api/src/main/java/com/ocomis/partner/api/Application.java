package com.ocomis.partner.api;

import com.ocomis.partner.domain.PartnerEventPublisher;
import com.ocomis.partner.infrastructure.AwsPartnerEventPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PartnerEventPublisher getPartnerEventPublisher() {
        return new AwsPartnerEventPublisher();
    }
}
