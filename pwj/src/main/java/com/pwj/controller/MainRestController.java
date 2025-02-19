package com.pwj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwj.service.MainService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MainRestController {
	@Autowired
	private MainService mainService;
	@GetMapping("/signup/checkParam")
	public Boolean signup(@RequestParam("param_name") String param_name, @RequestParam("param_value") String param_value) {
		// param_name :  id, nickname과 value를 받아서
		System.out.println("들어옴");
		// 서비스로 넘긴다.
		
		return mainService.checkParam(param_name, param_value);
//		if(mainService.checkParam(param_name,param_value)) return ResponseEntity.ok("true");
//		else return ResponseEntity.ok("false");
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<Object> apiLogin(HttpServletRequest request){
		System.out.println("들어옴: "+ request.getLocalAddr());
		
		return ResponseEntity.ok("success");
	}
}
