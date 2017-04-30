package com.example.springmvc.front.services;

import java.util.List;

import com.example.springmvc.front.model.User;

/**
 * 
 * @author henryyue
 *
 */
public interface IUserService {

	public List<User> getUsersByCompanyId(Integer id);

	public User findUserByLoginName(String username);

	public String getUserNameById(Integer id);

	public void addUser(User user);
}
