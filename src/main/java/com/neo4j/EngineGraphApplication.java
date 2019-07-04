package com.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ImportResource({ "classpath:*.xml" })
@EnableJpaRepositories(basePackages = "com.*")
@EntityScan("com.*")
public class  	EngineGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineGraphApplication.class, args);
	}

}

