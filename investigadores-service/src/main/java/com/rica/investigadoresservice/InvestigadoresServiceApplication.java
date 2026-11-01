package com.rica.investigadoresservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com\\.rica\\.investigadoresservice\\.investigadores\\.(adaptadores\\.entrada\\.InvestigadorController|dominio\\.InvestigadorRegistradoListener)"))
@EnableScheduling
public class InvestigadoresServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestigadoresServiceApplication.class, args);
    }
}