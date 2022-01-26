package com.sepehr.activity_notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableMongoRepositories // To use mongodb repositories
@EnableMongoAuditing // Annotation processing on mongo entities
public class ActivityNotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityNotebookApplication.class, args);
	}

}
