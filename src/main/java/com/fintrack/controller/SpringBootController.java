package com.fintrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootController {

	@GetMapping(value = "/hello" ) 
	public String hello() {
		return "Hello World";
	}
}
