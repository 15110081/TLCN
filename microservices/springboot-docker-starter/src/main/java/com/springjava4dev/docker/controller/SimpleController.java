package com.springjava4dev.docker.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	private Logger logger = LogManager.getLogger(this.getClass());
	@RequestMapping(value="/")
	public String home(){
		String info ="Congratulations! You had delpoy your app on Docker successfully!";
		logger.info(info);
		return info;
	}
}