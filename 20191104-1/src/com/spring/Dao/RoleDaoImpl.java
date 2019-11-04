package com.spring.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.pojo.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jt;
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public void save(Role role) {
		
		String sql=" INSERT INTO ar_role ( "
				+ "rname,"
				+ " alias"
				+ " ) VALUES (?,?)";
		jt.update(sql, role.getRname(),role.getAlias());
		
		
	}

	@Override
	public void delete(Integer id) {
		String sql= "delete from ar_role where rid = ?";
		jt.update(sql, id);
		
	}

	@Override
	public void update(Role role) {
		String sql = "update ar_role set rname = ?, alias = ? where rid = ?";
		jt.update(sql, role.getRname(),role.getAlias(),role.getRid());
		
	}

	@Override
	public Role getById(Integer id) {
		String sql = " select * from ar_role where rid = ? ";
		Role role= jt.queryForObject(sql, new RowMapper<Role>(){

			@Override
			public Role mapRow(ResultSet rs, int index) throws SQLException {
				 return mapRowHandler(rs);

			}
		} , id);
		return role;
	}

	@Override
	public List<Role> getAll() {
		String sql = " select * from ar_role";
		List<Role> list = jt.query(sql, new RowMapper<Role>(){

			@Override
			public Role mapRow(ResultSet rs, int index) throws SQLException {
				 return mapRowHandler(rs);
			}
		} );
		return list;
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(1) from ar_role ";
		Integer count = jt.queryForObject(sql, Integer.class);
		return count;
	}
	
	
	//结果映射处理器
	private Role mapRowHandler(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setRname(rs.getString("rname"));
		role.setAlias(rs.getString("alias"));
		role.setRid(rs.getInt("rid"));
		return role;
	}
	
	
}
