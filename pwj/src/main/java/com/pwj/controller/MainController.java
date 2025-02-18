package com.pwj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pwj.dto.UserDto;
import com.pwj.service.MainService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/login")
	public String login(HttpServletRequest request,Model model) {
		return "login";
	}
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute UserDto userDto) {
		System.out.println(userDto.toString());
		mainService.createUser(userDto);
		return "redirect:/login";
	}
	
}
