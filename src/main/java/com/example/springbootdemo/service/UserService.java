package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.UserBean;

import java.util.List;

public interface UserService {

    List<UserBean> selectU();

    UserBean login(String name, String password);

    void addU(int num, String uname, String upwd);
}
