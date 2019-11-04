- 创建pojo.Role.java

- 创建RoleDao.java 和 Role

  - 接口

  - ```java
    public interface ReloDao {
    	
    	void save(Role role);
    	
    	void delete(Integer id);
    	
    	void update(Role role);
    	
    	Role getById(Integer id);
    	
    	List<Role> getAll();
    	
    	int getTotalCount();
    	
    }
    
    ```

  - 实现

  - ```java
    package com.spring.Dao;
    public class RoleDaoImpl implements ReloDao{
    	
    	JdbcTemplate jt;
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
    		String sql = "update ar_role set rname = ?, alias = ? where id = ?";
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
    ```

- 创建spring配置文件

  - ```xml
    <context:property-placeholder location="classpath:db.properties" />
    <!-- 连接池 -->
    	<bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    		<property name="driverClass" value="${jdbc.driver}"></property>
    		<property name="jdbcUrl" value="${jdbc.url}"></property>
    		<property name="user" value="${jdbc.username}"></property>
    		<property name="password" value="${jdbc.password}"></property>
    	</bean>
    	
    	<!-- JdbcTemplate -->
    	<bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    		<property name="dataSource" ref="dataSource"></property>
    	</bean>
    	
    	<!-- roleDao -->
    	<bean name="roleDao" class="com.spring.Dao.RoleDaoImpl">
    		<property name="jt" ref="jdbcTemplate"></property>
    	</bean>
    ```

- 测试

  - ```java
    package com.spring.jdbctemplate;
    
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
    
    ```

    

- 使用注解的方式管理 bean 对象

  - 启动注解扫描

  - ```xml
    	<context:component-scan base-package="com.spring.dao"></context:component-scan>
    ```

  - 可以省去配置Dao节点

  - ```xml
    	<!-- roleDao -->
    	<!-- <bean name="roleDao" class="com.spring.Dao.RoleDaoImpl">
    		<property name="jt" ref="jdbcTemplate"></property>
    	</bean> -->
    ```

  - 配置注解

  - ```java
    @Repository("roleDao")
    public class RoleDaoImpl implements RoleDao{
      
      @Resource(name="jdbcTemplate")
        private JdbcTemplate jt;
    ```

    或者

    ```java
    @Repository() //默认名字 roleDaoImpl
    public class RoleDaoImpl implements RoleDao{
      ****
        @Resource(name="roleDaoImpl")
          private RoleDao roleDao;
          //或者
        @Autowired
          private RoleDao roleDao;
    ```

  - 测试