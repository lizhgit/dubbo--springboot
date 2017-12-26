package com.test.service;



import com.test.entity.User;
import com.test.dao.UserDao;
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
	public List<User> checkUser(User user) {
		List<User> userList=userDao.checkUser(user);
		return userList;
	}
	public int insertUser(User user){
		return userDao.insertUser(user);
	}
}
