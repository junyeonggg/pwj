package com.pwj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf->csrf.ignoringRequestMatchers("/api/**"))
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/", "/api/login","/login", "/signup/**", "/js/**", "/css/**")
								.permitAll().anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/"))
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/").invalidateHttpSession(true));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("http://localhost:3000"); // React 앱의 주소 허용
	    config.addAllowedMethod("GET"); // 허용되는 HTTP 메서드
	    config.addAllowedMethod("POST");
	    config.addAllowedHeader("Content-Type"); // 허용되는 헤더
	    config.addAllowedHeader("Authorization"); // Authorization 헤더 허용
//	    config.setAllowCredentials(true); // 쿠키나 인증 정보도 허용
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 CORS 설정 적용
	    return source;
	}
}
