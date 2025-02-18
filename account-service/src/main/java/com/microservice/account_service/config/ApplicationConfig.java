package com.microservice.account_service.config;

import com.microservice.account_service.feignclient.DepartmentFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper initModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }

}