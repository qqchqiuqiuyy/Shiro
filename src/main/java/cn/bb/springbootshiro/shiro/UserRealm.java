package cn.bb.springbootshiro.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/*
自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
   /*
   执行授权逻辑
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       System.out.println("执行授权逻辑");
        return null;
    }


    /*
    执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //假定user pwd
        String user = "bobo";
        String password = "123";
        //编写shiro判断逻辑,判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if(!token.getUsername().equals(user)){
            //shiro底层会自动抛出UnknownAccountException
            return null;
        }
           //密码校验的话 返回这个
        /*参数
        1.对象 user
        2.密码  user.getpassword
        3.relam  当前relam 可以是UserRealm
        这里的话 就先传password其他空也行
         */
        return new SimpleAuthenticationInfo("",password,"");
    }
}
