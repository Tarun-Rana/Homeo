package com.dit.Homeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class HomeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeoApplication.class, args);
	}

}
