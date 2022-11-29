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
        UserBean userBean=userService.login(name,password);
        if (userBean!=null){
            return "home";
        }
        else {
            String rs="登录信息";
            rs="姓名或密码错误";
            model.addAttribute("rs",rs);
            return "loginView";
        }
    }
}
