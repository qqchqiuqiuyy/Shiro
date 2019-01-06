package cn.bb.springbootshiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
shiro配置类
 */
@Configuration
public class ShiroConfig {

    /*
    创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /*
                常用过滤器:
                    anon: 无需认证(登录) 可以访问
                    authc: 必须认证才可以访问
                    user: 如果使用rememberMe的功能可以直接访问
                    perms: 该资源必须得到资源权限才可以访问
                    role: 该资源必须得到角色权限才可以访问
         */
        //设置拦截路径 和 过滤器  拦截这些请求URL(controller) 如果没有对应的权限就会定向到login页面
        Map<String,String> filterMap = new LinkedHashMap<>(); //只能用Link

        /* filterMap.put("/toAdd","authc");
        filterMap.put("/toUpdate","authc");*/

        //放行 要写在通配前面
        filterMap.put("/test","anon");

         //放行login表单
         filterMap.put("/login","anon");

       //授权过滤器 对这个路径Url进行授权拦截
        //拦截后 shiro会自动跳转到未授权页面 需要设置
        //下面两个是对不同Url 所需要的资源访问权限限制
        /*filterMap.put("/toAdd","perms[user:add]");
        filterMap.put("/toUpdate","perms[user:update]");*/

        //用角色 role
        filterMap.put("/toAdd","roles[boss]");
        filterMap.put("/toUpdate","roles[putong]");

        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/toUnAuth");

        //通配方法 让某个目录所有页面都实现过滤
        filterMap.put("/*","authc");

        //修改认证失败后跳转页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }



    /*
    创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /*
    创建Realm
     */
    @Bean("userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    /*
    配置shiroDialect 用于thymelefa和shiro标签配合使用
     */
    @Bean("shiroDialect")
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
