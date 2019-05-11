
-  实现了userdetailservice or 没有加注解@EnableWebSecurity  启动时控制台不会自动生成密码

- java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"？


- 只加了注解@EnableAuthorizationServer 没有自己实现AuthorizationServerConfigurerAdapter ok
- AuthorizationServerConfigurerAdapter 自己实现后curl 没有反应
- {"timestamp":"2019-04-11T11:50:58.912+0000","status":500,"error":"Internal Server Error","message":"There is no PasswordEncoder mapped for the id \"null\"","path":"/oauth/token"}% ？


- oauth2.0与security怎么结合的


- 自己加了 passwordEncoder @bean后，密码校验通不过， 注释掉后，用以下命令获取access_token 成功
```
curl 73fd74c4dfd3:0bc2b785d173419ca4e0948c90896b06@localhost:8089/oauth/token -d grant_type=password -d username=user -d password=9894d9bf-72c9-4df0-8496-cb82f2fc341e
```
- 
```
{"error":"invalid_grant","error_description":"Bad credentials"}%   
  
```
- userdetailservice 增加 new BCryptPasswordEncoder().encode(password)

```
Caused by: java.lang.IllegalStateException: No supported DataSource type found
```

- if use jdbc, please folloing in AuthorizationServerConfigurer ,don't use client.jdbc . it has a bug that don't fixed it
```
  @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.withClientDetails(new JdbcClientDetailsService(dataSource))//memory or jdbc or other
                ...
    }

```

url 不带client_id 不会走Oauth2的过滤器

数据库缺少 user 和 authriy 表

- 使用注解 //@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) 后， 程序启动报错

```
org.springframework.beans.factory.support.BeanDefinitionOverrideException: Invalid bean definition with name 'metaDataSourceAdvisor' defined in null: Cannot register bean definition [Root bean: class [org.springframework.security.access.intercept.aopalliance.MethodSecurityMetadataSourceAdvisor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null] for bean 'metaDataSourceAdvisor': There is already [Root bean: class [org.springframework.security.access.intercept.aopalliance.MethodSecurityMetadataSourceAdvisor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null] bound.
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.registerBeanDefinition(DefaultListableBeanFactory.java:897) ~[spring-beans-5.1.6.RELEASE.jar:5.1.6.RELEASE]
	at org.springframework.security.config.annotation.method.configuration.MethodSecurityMetadataSourceAdvisorRegistrar.registerBeanDefinitions(MethodSecurityMetadataSourceAdvisorRegistrar.java:59) ~[spring-security-config-5.1.5.RELEASE.jar:5.1.5.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.lambda$loadBeanDefinitionsFromRegistrars$1(ConfigurationClassBeanDefinitionReader.java:364) ~[spring-context-5.1.6.RELEASE.jar:5.1.6.RELEASE]
	at java.util.LinkedHashMap.forEach(LinkedHashMap.java:684) ~[na:1.8.0_181]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitionsFromRegistrars(ConfigurationClassBeanDefinitionReader.java:363) ~[spring-context-5.1.6.RELEASE.jar:5.1.6.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitionsForConfigurationClass(ConfigurationClassBeanDefinitionReader.java:145) ~[spring-context-5.1.6.RELEASE.jar:5.1.6.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitions(ConfigurationClassBeanDefinitionReader.java:117) ~[spring-context-5.1.6.RELEASE.jar:5.1.6.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions(ConfigurationClassPostProcessor.java:327) ~[spring-context-5.1.6.RELEASE.jar:5.1.6.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(ConfigurationClassPostProcessor.java:232) ~[spring-context-5.1.6.RELEASE.jar:5.1.6.RELEASE]

```

