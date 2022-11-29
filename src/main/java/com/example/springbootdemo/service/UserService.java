package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.UserBean;

public interface UserService {
    UserBean login(String name,String password);
}
