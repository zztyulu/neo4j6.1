package com.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class  	EngineGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineGraphApplication.class, args);
	}

}

