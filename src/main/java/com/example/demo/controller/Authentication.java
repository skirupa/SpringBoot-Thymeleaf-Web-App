package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authentication {
	
	@GetMapping("/login")
	public String index() {
		return "Home page";
	}
	
}
