package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.pojo.User;
import com.springmvc.service.IUserService;
@Service
public class UserService implements IUserService {
	private List<User> list = new ArrayList<User>();
	
	public UserService() {
		list.add( new User(1, "Tom", "T123456", "Ã¿ƒ∑"));
		list.add( new User(2, "Mary", "M123456", "¬Í¿ˆ"));
		list.add( new User(3, "Rose", "R123456", "»·Àø"));
		list.add( new User(4, "Ben", "B123456", "±ø±ø"));
		list.add( new User(5, "Smith", "S123456", " ∑√‹Àπ"));
	}

	@Override
	public User checkLogin(User user) {
		User u= null;
		for(User ur : list) {
			if(ur.getUsername().equals(user.getUsername()) && ur.getPassword().equals(user.getPassword())) {
				u = ur;
				break;
			}
		}
		return u;
	}

	@Override
	public List<User> getAllUsers() {
		return list;
	}

	@Override
	public User findUserByName(String name) {
		User user = new User();
		
		for(User u : list) {
			if(u.getUsername().equals(name) || u.getNick().equals(name)) {
				user = u;
				break;
			}
		}
		return user;
	}

}
