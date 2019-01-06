package cn.bb.springbootshiro.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/test")
    public String toTest(){
        return "test";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){
        return "user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        /*
        shiro认证操作
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法 之后shiro会去执行登录操作 没异常表示成功,之后回去Relam执行认证操作
        try {
            subject.login(token);
            //登录成功
            return "forward:/test";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败 : 用户不存在
            model.addAttribute("msg","user no found ");
            return "login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "login";

        }
    }
}
