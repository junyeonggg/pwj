package com.pwj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pwj.dao.Userdao;

@Service
public class SecurityService implements UserDetailsService{
	@Autowired
	private Userdao userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		userDao.findById(userid);
		return null;
	}
	
}
