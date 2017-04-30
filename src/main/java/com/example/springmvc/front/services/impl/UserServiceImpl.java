package com.example.springmvc.front.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.front.dao.UserDao;
import com.example.springmvc.front.model.User;
import com.example.springmvc.front.services.IUserService;

/**
 * 
 * @author henryyue
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	public List<User> getUsersByCompanyId(Integer id) {
		return userDao.getUsersByCompanyId(id);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	public User findUserByLoginName(String username) {
		return this.userDao.findUserByLoginName(username);
	}

	public String getUserNameById(Integer id) {
		return this.userDao.getUserNameById(id);
	}

}
