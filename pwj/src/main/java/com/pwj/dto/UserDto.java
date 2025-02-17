package com.pwj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {
	private int id;
	private String userid;
	private String pwd;
	private String nickname;
}
