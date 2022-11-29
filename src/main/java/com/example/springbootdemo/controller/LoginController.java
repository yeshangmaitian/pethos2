package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.UserBean;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
@Controller
public class LoginController {
    @Resource
    UserService userService;

    @RequestMapping("/loginView")
    public String showLogin(){
        return "loginView";
    }

    @RequestMapping("/home")
    public String showHome(){
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginMethod(String name,String password,Model model){
        String msg="登录信息";
        if(name.isEmpty()||password.isEmpty()){
            if(name.isEmpty()){
                msg="姓名不能为空";
            }
            else {
                msg ="密码不能为空";
            }
            model.addAttribute("msg",msg);
            return "loginView";
        }
        UserBean userBean=userService.login(name,password);
        if (userBean!=null){
            msg="login successfully";
            model.addAttribute("msg",msg);
            return "home";
        }
        else {
            msg="姓名或密码错误";
            model.addAttribute("msg",msg);
            return "loginView";
        }
    }
}