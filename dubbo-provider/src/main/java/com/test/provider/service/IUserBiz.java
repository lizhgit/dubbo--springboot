package com.test.provider.service;

import java.util.List;

import com.test.provider.entity.User;

public interface IUserBiz {
	List<User> findUser(User user);
	int insertUser(User user);
}
