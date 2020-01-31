package com.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootApiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiTestApplication.class, args);
	}
}
