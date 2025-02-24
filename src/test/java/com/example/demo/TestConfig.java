package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public DataSourceWrapper dataSourceWrapper() {
        return new DataSourceWrapper();
    }

}