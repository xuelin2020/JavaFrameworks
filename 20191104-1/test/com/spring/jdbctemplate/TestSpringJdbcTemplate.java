package com.spring.jdbctemplate;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.spring.Dao.RoleDao;
import com.spring.pojo.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpringJdbcTemplate {
	
	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	@Test
	public void testSave() throws Exception{
		Role role = new Role();
		role.setRname("spring1");
		role.setAlias("spring2");
		roleDao.save(role);
	}
	
	@Test
	public void testDelete() {
		roleDao.delete(3);
	}
	
	@Test
	public void testUpdate() {
		Role role = new Role();
		role.setRname("spring11");
		role.setAlias("spring22");
		role.setRid(4);
		roleDao.update(role);
	}
	
	@Test
	public void testGetById() {
		Role role = roleDao.getById(4);
		System.out.println(role);
	}
	
	@Test
	public void testGetAll() {
		List<Role> list = roleDao.getAll();
		System.out.println(list);
	}
	
	@Test
	public void testGetTotalCount() {
		Integer count = roleDao.getTotalCount();
		System.out.println(count);
	}
	
}
