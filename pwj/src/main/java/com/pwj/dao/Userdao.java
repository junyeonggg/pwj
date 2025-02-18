package com.pwj.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pwj.dto.UserDto;

@Mapper
public interface Userdao {

	@Select("SELECT * FROM user_tbl WHERE user_id = #{user_id}")
	UserDto findById(String user_id);

	@Select("SELECT * FROM user_tbl WHERE #{param_name} = #{param_value}")
	Optional<UserDto> findByParam(@Param("param_name") String param_name,@Param("param_value") String param_value);

	@Insert("INSERT INTO user_tbl(user_id,nickname,password) VALUES(#{userid},#{nickname},#{pwd})")
	void createUser(UserDto userDto);
	
}
