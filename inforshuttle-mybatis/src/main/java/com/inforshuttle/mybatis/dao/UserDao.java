package com.inforshuttle.mybatis.dao;

import java.util.List;

import com.inforshuttle.mybatis.dto.UserDto;

public interface UserDao {
	public List<UserDto> getUsers(UserDto user) throws Exception;
}
