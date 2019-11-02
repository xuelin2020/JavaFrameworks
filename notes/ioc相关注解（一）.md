### 以往MVC的架构

创建一个Dao接口

```java
package com.dao;
public interface IXDao {
	public void select();
}
```

创建一个Dao实现接口

```java
package com.dao;
public class XDaoImpl implements IXDao{
	@Override
	public void select() {
		System.out.println("XDao执行了 select 操作");
	}
}
```

创建一个 Service 接口

```java
package com.service;
public interface IXXXService {
	public void select();
}
```

创建一个Service接口的实现

```java
package com.service;
import com.dao.IXDao;

public class XServiceImpl implements IXService{
	IXDao dao;
	public IXDao getDao() {
		return dao;
	}
	public void setDao(IXDao dao) {
		this.dao = dao;
	}
	@Override
	public void select() {
		System.out.println("XServiceImpl - 执行了select()");
		dao.select();
	}
}

```

创建 Controller 类

```java
package com.controller;
import com.service.IXService;
public class XController {
	IXService service;
	public IXService getService() {
		return service;
	}
	public void setService(IXService service) {
		this.service = service;
	}
	public void doGet() {
		System.out.println("XController--执行了doGet()");
		service.select();
	}
}
```

创建xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="daoImpl" class="com.dao.XDaoImpl"></bean>
<bean id="serviceImpl" class="com.service.XServiceImpl">
	<property name="dao" ref="daoImpl"></property>
</bean>
<bean id="controller" class="com.controller.XController">
	<property name="service" ref="serviceImpl"></property>
</bean>

</beans>

```

创建一个测试类

```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.controller.XController;;

public class Demo1 {
	public static void main(String[] args) {
		
	ApplicationContext app = new ClassPathXmlApplicationContext("application-mvc.xml");
	XController controller = app.getBean(XController.class);
	
	controller.doGet();
	
	}
}
```

得到结果

<img src="/Users/xuelin/Documents/Github/JavaFrameworks/notes/img/image-20191101215231621.png" alt="image-20191101215231621" style="zoom:50%;" />

### 使用 Spring 的注解方式管理

Dao 增加注解

```java
@Repository("daoImpl")
public class XDaoImpl implements IXDao{
```

 Service 增加注解

```java
@Service("serviceImpl")
public class XXXServcieImpl implements IXXXService{
	@Resource(name="daoImpl")
	IXXXDao dao ;
```

 Controller 增加注解

```java
@Controller
public class XXXController {
	@Resource(name="serviceImpl")
	IXXXService service;
```

Namespaces 导入 context

<img src="/Users/xuelin/Documents/Github/JavaFrameworks/notes/img/image-20191101215028746.png" alt="image-20191101215028746" style="zoom:50%;" />

重写配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">
<context:component-scan base-package="com"></context:component-scan>
</beans>
```

执行测试类

得到同样结果

<img src="/Users/xuelin/Documents/Github/JavaFrameworks/notes/img/image-20191101215214587.png" alt="image-20191101215214587" style="zoom:50%;" />