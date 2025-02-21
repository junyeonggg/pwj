package com.pwj.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pwj.provider.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 로그인 후 요청들
		// - 토큰을 추출하여 유효검증을 실시한다.
		 String token = jwtTokenProvider.resolveToken(request);
		 System.out.println("시스템 : 토큰 ["+token+"]");
		// - 검증에 통과하면 authentication 에 인증 정보를 저장하고 다음 필터로 넘긴다.
		if(jwtTokenProvider.validateToken(token)) {
			System.out.println("시스템 : [토큰 검증에 통과했습니다.]");
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		// context Holder 에 authentication 객체를 저장하고 넘긴다. doFilter
		filterChain.doFilter(request, response);
	}

}
