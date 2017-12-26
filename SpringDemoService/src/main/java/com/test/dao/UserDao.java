package com.test.dao;
import java.util.List;

import com.test.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {
	int insertUser(User user);
	List<User> findUser(User user);
	List<User> checkUser(User user);
}