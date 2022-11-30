package com.example.springbootdemo.serviceImpl;

import com.example.springbootdemo.bean.UserBean;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserBean> selectU() {
        return userMapper.selectU();
    }

    @Override
    public UserBean login(String name, String password) {
        return userMapper.getInfo(name, password);
    }

    @Override
    public void addU(int num, String uname, String upwd) {
        userMapper.addU(num, uname, upwd);
    }
}