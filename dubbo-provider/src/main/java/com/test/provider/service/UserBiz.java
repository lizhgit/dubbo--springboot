package com.test.provider.service;



import com.test.provider.dao.UserDao;
import com.test.provider.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;

public class UserBiz implements IUserBiz {

	@Autowired
	@Qualifier("userDao")
    private UserDao userDao;
	public List<User> findUser(User user) {
		List<User> userList=userDao.findUser(user);
		return userList;
	}
	public int insertUser(User user){
		return userDao.insertUser(user);
	}
}
