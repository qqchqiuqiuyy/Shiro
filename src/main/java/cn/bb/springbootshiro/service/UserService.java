package cn.bb.springbootshiro.service;

import cn.bb.springbootshiro.entity.User;
import cn.bb.springbootshiro.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findByName(String name){
        return userMapper.findByName(name);
    }

    public User findById(Integer id){
        return userMapper.findById(id);
    }
}
