package com.pwj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
	@GetMapping("/signup/checkParam")
	public String signup(@RequestParam("param_name") String param_name, @RequestParam("param_value") String value) {
		
		
		return "";
	}
}
