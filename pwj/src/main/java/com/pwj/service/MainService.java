package com.pwj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwj.dao.Userdao;

@Service
public class MainService {
	@Autowired
	private Userdao userDao;
	public Boolean checkParam(String param_name, String param_value) {
			return userDao.findByParam(param_name,param_value).isEmpty(); 
	}

}
