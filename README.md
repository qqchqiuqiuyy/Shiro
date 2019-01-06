# Shiro
关于权限的学习

## 各个类用处
- ShiroConfig 用来配置一些bean 交给spring管理 
 ShiroFilterFactoryBean 用来设置什么路径是否需要过滤和过滤角色和资源许可 设置未授权提示页面和认证失败后页面
- DefaultWebSecurityManager 关联相关了Realm
- UserRealm 自定义Relam 继承 AuthorizingRealm,之后要重写两个方法 第一个方法是给资源授权,第二个方法是执行认证
- ShiroDialect用来thymeleaf使用
- UserController 里面有关于访问的
