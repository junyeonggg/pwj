package com.pwj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwj.dto.UserDto;
import com.pwj.service.JwtAuthService;
import com.pwj.service.MainService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class MainRestController {
	@Autowired
	private MainService mainService;
	@Autowired
	private JwtAuthService jwtAuthService;

	@GetMapping("/signup/checkParam")
	public Boolean signup(@RequestParam("param_name") String param_name,
			@RequestParam("param_value") String param_value) {
		// param_name : id, nickname과 value를 받아서
		System.out.println("들어옴");
		// 서비스로 넘긴다.

		return mainService.checkParam(param_name, param_value);
//		if(mainService.checkParam(param_name,param_value)) return ResponseEntity.ok("true");
//		else return ResponseEntity.ok("false");
	}

	@PostMapping("/api/login")
	public ResponseEntity<?> apiLogin(@RequestBody UserDto userDto, HttpServletRequest request,
			HttpServletResponse response) {
		// 1. 처음 로그인을 시도할 때
		// - db의 id, pw를 비교해 일치하면 토큰을 생성하여 클라이언트의 헤더에 저장한다.
		String token = jwtAuthService.attemptAuthentication(userDto);
		if (token != null) {
			Cookie cookie = new Cookie("JWT-TOKEN", token);
			response.addCookie(cookie);
		}
		System.out.println("시스템 : [발생된 토큰 : "+token+" ]");
		return ResponseEntity.ok(token);
	}

	@GetMapping("/api/test")
	public ResponseEntity<Object> text() {
		return ResponseEntity.ok("text");
	}
}
