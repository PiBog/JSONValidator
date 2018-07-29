package ua.pp.sola.jsonvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class JsonvalidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonvalidatorApplication.class, args);
	}
}
