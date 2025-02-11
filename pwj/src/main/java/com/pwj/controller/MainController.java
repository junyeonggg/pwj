package com.pwj.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/login")
	public String login(HttpServletRequest request,Model model) {
		return "login";
	}
}
