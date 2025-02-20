package com.pwj.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pwj.dao.Userdao;
import com.pwj.dto.UserDto;
import com.pwj.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtAuthService {
	private final Userdao userdao;
	private final JwtTokenProvider jwtTokenProvider;
	
	public String attemptAuthentication(UserDto userDto) {
		// db에 있는 유저인지 확인, 없을 수도 있음
		UserDto user = userdao.findById(userDto.getUserid());
		System.out.println("시스템 : [db에서 유저를 가져옵니다.] -> "+user.toString());
		// 비밀번호 일치 여부 확인
		System.out.println("시스템 : [비밀번호 일치 확인] -> ");
		if(match(user.getPwd(),userDto.getPwd())) {
			// 일치하면 토큰 생성
			System.out.println("시스템 : [일치하므로 토큰을 생성합니다.]");
			String token = jwtTokenProvider.createToken(user.getUserid());
			System.out.println("시스템 : [토큰 생성 완료]");
			return token;
		}
		System.out.println("시스템 : [비밀번호가 달라 토큰을 발급안함]");
		return null;
	}
	
	private Boolean match(String pwd1, String pwd2) {
		return pwd1.equals(pwd2);
//		return pwd1.equals(passwordEncoder.encode(pwd2));
	}

}
