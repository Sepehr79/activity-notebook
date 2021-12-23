package com.sepehr.activity_notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ActivityNotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityNotebookApplication.class, args);
	}

}
