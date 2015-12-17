package com.example.springmvc.front.dao;

import java.util.List;

import com.example.springmvc.front.model.User;

/**
 * 
 * @author henryyue
 *
 */
public interface UserDao {

	public List<User> getUsersByCompanyId(Integer companyId);
	
	public void addUser(User user);
	
	public User findUserByLoginName(String username);
	
	public String getUserNameById(Integer id);
}
