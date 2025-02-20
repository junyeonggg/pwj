package com.pwj.provider;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pwj.service.SecurityService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {
	@Autowired
	private SecurityService securityService;
	private static final Key SECRET_KEY = Keys.hmacShaKeyFor("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes());

	public String createToken(String userid) {
		Date expiryDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
		return Jwts.builder()
			.setSubject(userid)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(SECRET_KEY)
			.compact();
			
	}

	public String resolveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token != null) {
			token = token.substring(7);
		}
		System.out.println("시스템 : [헤더에서 토큰을 추출합니다.]");
		return token;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println("토큰 유효 체크 예외 발생");
			return false;
		}
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = securityService.loadUserByUsername(getUsername(token));
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

	private String getUsername(String token) {
		String userid = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
		return userid;
	}

}
