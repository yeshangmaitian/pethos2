package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.UserBean;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Controller
public class userController {
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

    @RequestMapping("/addUser")
    public String showAddUser(){
        return "addUser";
    }

    @RequestMapping("/selectUser")
    public String showSelectUser(){
        return "selectUser";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginMethod(String name,String password,Model model){
        String msg="登录信息";
        if(name.isEmpty()||password.isEmpty()){
            if(name.isEmpty()){
                msg="姓名不能为空";
                String upwd=password;
                model.addAttribute("upwd",upwd);
            }
            else {
                msg ="密码不能为空";
                String uname=name;
                model.addAttribute("uname",name);
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
            String uname=name;
            model.addAttribute("uname",name);
            String upwd=password;
            model.addAttribute("upwd",upwd);
            msg="姓名或密码错误";
            model.addAttribute("msg",msg);
            return "loginView";
        }
    }
    @RequestMapping(value = "/selectU",method = RequestMethod.POST)
    public String selectUMethod(Model model){
        List<UserBean> userList=userService.selectU();
        String tableHeaders="<tr><th>编号</th><th>姓名</th><th>密码</th></tr>";
        String usersHTML = "";
        usersHTML += tableHeaders;
        Iterator<UserBean> iterator =userList.iterator();
        String pHTML;
        while (iterator.hasNext()) {
            UserBean a = iterator.next();
            pHTML="<tr><td>"+a.getId()+"</td><td>"+a.getUname()+"</td><td>"+a.getUpwd()+"</td></tr>";
            usersHTML += pHTML;
        }
        String msg="查询结果如下";
        model.addAttribute("msg",msg);
        model.addAttribute("rs","<table border=\"1\" cellspacing=\"0\" id='table'>"+usersHTML+"</table>");
        return "selectUser";
    }
    @RequestMapping(value = "/addU",method = RequestMethod.POST)
    public String addMethod(int num, String uname, String upwd,Model model){
        String msg = null;
        model.addAttribute("num", num);
        model.addAttribute("uname", uname);
        model.addAttribute("upwd", upwd);
        System.out.println("1num:"+num+"name:"+uname+"pwd"+upwd);
        if (uname.isEmpty() || upwd.isEmpty() ) {
            if (uname.isEmpty()) {
                msg = "姓名不能为空";
                model.addAttribute("msg", msg);
                return "addUser";
            }
            if (upwd.isEmpty()) {
                msg = "密码不能为空";
                model.addAttribute("msg", msg);
                return "addUser";
            }
        }
        userService.addU(num,uname,upwd);
        System.out.println("2num:"+num+"name:"+uname+"pwd"+upwd);
        msg="添加成功";
        model.addAttribute("msg",msg);
        return "addUser";
    }
}