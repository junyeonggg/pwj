package com.pwj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pwj.dao.Userdao;
import com.pwj.dto.UserDto;

@Service
public class SecurityService implements UserDetailsService{
	@Autowired
	private Userdao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		UserDto user = userDao.findById(userid);
		System.out.println(user.toString());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(user.getUserid(),passwordEncoder.encode(user.getPwd()),authorities);
	}

	public UserDetails loadUserByUsername(String username, String string) {
		System.out.println("loadUserByUsername jwt 들어옴 username : "+username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(username,"",authorities);
	}
	
}
