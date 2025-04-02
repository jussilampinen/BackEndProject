package sof03.project.projectship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectshipApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(){
		return (args) -> {

		};
	}

}
