package com.test.dao;
import java.util.List;

import com.test.provider.entity.User;

public interface UserDAO {
	public int insertUser(User user);
	public int updateUser(User user);
	public List<User> findUser(User user);
}