package com.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({ "classpath:*.xml" })
public class  	EngineGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineGraphApplication.class, args);
	}

}

