package com.spring.Dao;

import java.util.List;

import com.spring.pojo.Role;

public interface RoleDao {
	
	void save(Role role);
	
	void delete(Integer id);
	
	void update(Role role);
	
	Role getById(Integer id);
	
	List<Role> getAll();
	
	int getTotalCount();
	
}
