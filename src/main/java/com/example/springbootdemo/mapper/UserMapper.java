package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.UserBean;

import java.util.List;

public interface UserMapper {

    UserBean getInfo(String name, String password);

    List<UserBean> selectU();

    void addU(int num, String uname, String upwd);
}
