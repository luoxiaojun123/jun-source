# jun-source
jun-source 是一个以权限管理开始的项目，重在将我平时学的，和一些经典的东西通过代码的方式实现

本项目所使用的技术（后续增加）：
- 后端：spring,shiro,dubbo,mybatis，zookeeper
- 前端：vue.js,ztree,jqgrid

本项目中技术点，问题，以及解决方案（后续增加）
- 由于选用的RPC框架是dubbo，dubbo现在也不更新，使用的spring版本低，所以会跟项目中依赖的高版本spring冲突 解决方法
		!<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>dubbo</artifactId>
			<version>${dubbo-version}</version>
			<exclusions>
				<exclusion>
					<artifactId>spring</artifactId>
					<groupId>org.springframework</groupId>
				</exclusion>
			</exclusions>
		!</dependency>
- 选用dubbo时为了避免xml 过于复杂 ，采用注解的方式实现服务的注册，但是 当使用注解事务时，不生效。原因是 dubbo的 @Service 注解式不能被继承的，所以修改
  注解@Service 让其可以继承
    @Inherited    加上这个
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ java.lang.annotation.ElementType.TYPE })
    public @interface Service
  但是这样的话，注册中心 注册的只是@Service修饰类的代理，所以需要 @Service(interfaceName = "com.xiaojun.service.SysRoleService") 解决
  
- 在 shiro 中使用dubbo的服务，由于dubbo使用注解，而我将shiro配置在，所以在shiro中不能通过@Reference 发现服务，所以 对于shiro引用服务 通过xml的方式配置，原因是spring加载配置文件时就将Realm实例化
- 本项目中数据源是通过jndi配置的 配置在tomcat context.xml 中 ，采用org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource 实现多数据源的切换 通过 @SlaveDataSource 直接在方法上实现数据的切换
- Mybatis 一次执行多条sql语句 修改数据库连接参数加上allowMultiQueries=true
- 使用 PageHelper 插件分页
