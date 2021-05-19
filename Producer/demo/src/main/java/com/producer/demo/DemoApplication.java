package com.producer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("com.producer.models")
@EnableJpaRepositories("com.producer.repository")
@EnableSwagger2
@EnableScheduling
@ComponentScan(basePackages ={
		"com.producer.controller",
		"com.producer.service",
		"com.producer.config",
		"com.producer.model",
		"com.producer.repository",
		"com.producer.utils",
		"com.producer.DTOs"} )
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
