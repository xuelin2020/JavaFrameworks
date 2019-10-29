## mybatis完成对数据库标的增删改查操作

标签：mybatis

****

**Contents**

- []()
- []()

------

## 映射文件

- `DeptMapper.xml`

```xml
 <mapper namespace="com.mybatis.dao.DeptMapper">
   	 
   	<select id="getAllDept" resultType="dept">
   		select * from dept
   	</select>
   	
   	<select id="getDeptByNo" resultType="dept">
   		select * from dept where deptno = ${dept}
   	</select>
   	
   	<insert id="addDept" parameterType="dept">
   		insert into dept value (default, #{dname},#{loc})
   	</insert>
   	
   	<delete id="delDeptName" parameterType="dept">
   		delete from dept where dname = ${deptname}
   	</delete>
   	
   	<update id="updateDept" parameterType="dept">
   		update dept set dname = ${name}, loc=${loc} where deptno=${deptno}
   	</update>
   	
   	<select id="getDeptByPage" resultType="dept">
   		select * from dept limit #{index},#{size}
   	</select>
   </mapper>
```

## 接口

- `DeptMapper.java`

```java
public interface DeptMapper {
	
	public List<Dept> getAllDept();
	
	public Dept getDeptByNo(int deptno);
	
	public int addDept(Dept dept);
	
	public int delDeptName(int no);
	
	public int updateDept(Dept dept);
	
	public List<Dept> getDeptByPage(Map<String,Integer> map);
	
}

```

## 小结

- 如果传一个参数，#{}
- 如果多个参数，map，key对应…… value对应
- 