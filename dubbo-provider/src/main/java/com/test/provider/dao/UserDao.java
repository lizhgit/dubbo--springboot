package com.test.provider.dao;
import java.util.List;

import com.test.provider.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {
	int insertUser(User user);
	List<User> findUser(User user);
}