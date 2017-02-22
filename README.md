# jun-source
jun-source 是一个以后台权限管理开始的项目，重在将我平时学的，和一些经典的东西通过代码的方式实现

项目结构：
- jun-api 公共接口，POJO，Utils类
- jun-source 项目管理
- jun-consumer-gateway 消费者网关，后台管理权限模块
- jun-consumer-web 消费者web 一般一些列子代码在这里写
- jun-provider-cache 提供者，缓存相关
- jun-provider-service 提供者，主要业务相关

本项目所使用的技术（后续更新）：
- 后端：spring,shiro,dubbo,mybatis,zookeeper,redis,rabbitmq,quartz集群,swagger2,druid
- 前端：vue.js,ztree,jqgrid

本项目中技术点，问题，以及解决方案（后续更新）
- 由于选用的RPC框架是dubbo，dubbo现在也不更新，使用的spring版本低，所以会跟项目中依赖的高版本spring冲突 解决方法 引用dubbo jar 包时 不依赖dubbo的spring
- 选用dubbo时为了避免xml 过于复杂 ，采用注解的方式实现服务的注册，但是 当使用注解事务时，不生效。原因是 dubbo的 @Service 注解式不能被继承的，所以修改
  注解@Service 让其可以继承。
  但是这样的话，注册中心 注册的只是@Service修饰类的代理，所以需要 @Service(interfaceName = "com.xiaojun.service.SysRoleService") 解决
  
- 在 shiro 中使用dubbo的服务，由于dubbo使用注解，而我将shiro配置在，所以在shiro中不能通过@Reference 发现服务，所以 对于shiro引用服务 通过xml的方式配置，原因是spring加载配置文件时就将Realm实例化
- 本项目中数据源是通过jndi配置的 配置在tomcat context.xml 中 ，采用org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource 实现多数据源的切换 通过 @SlaveDataSource 直接在方法上实现数据的切换
- Mybatis 一次执行多条sql语句 修改数据库连接参数加上allowMultiQueries=true
- 使用 PageHelper 插件分页
- jun-api  lib 下修改源码后的dubbo包和最新版支付宝sdk
- spring 集成redis  业务中通过AOP方式实现
- jun-consumer-web 集成支付宝支付(使用的是沙箱环境，可以自己在开放平台配置)，包括相关其他的支付宝接口  支付访问测试localhost:8080/v1/pay/initPayParams（测试数据写在代码中，需修改订单号） 同步和异步通知地址可以使用ngrok生成免费域名进行测试
- jun-consumer-web 微信授权登录
