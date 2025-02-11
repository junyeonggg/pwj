package com.pwj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pwj.dto.UserDto;

@Mapper
public interface Userdao {

	@Select("SELECT * FROM user_tbl WHERE user_id = #{user_id}")
	UserDto findById(String user_id);
	
}
