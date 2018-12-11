package com.javasampleapproach.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootStudentApplication {

	@RequestMapping(value = "/")
	public String available() {
		return "Welcome To Student Service!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudentApplication.class, args);
	}
}