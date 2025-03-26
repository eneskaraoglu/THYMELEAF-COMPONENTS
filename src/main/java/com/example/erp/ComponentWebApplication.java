package com.example.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ComponentWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentWebApplication.class, args);
	}

}
