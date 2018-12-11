package com.javasampleapproach.lecturerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootLecturerApplication {
	
	@RequestMapping(value = "/")
	public String welcome() {
		return "Welcome To Lecturer Service!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLecturerApplication.class, args);
	}
}