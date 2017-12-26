package com.test.service;

import com.test.entity.User;

import java.util.List;

public interface IUserBiz {
	List<User> findUser(User user);
	List<User> checkUser(User user);
	int insertUser(User user);
}
