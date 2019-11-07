package com.springmvc.service;

import java.util.List;

import com.springmvc.pojo.User;

public interface IUserService {
	public User checkLogin(User user);
	public List<User> getAllUsers();
	public User findUserByName(String name);
}
