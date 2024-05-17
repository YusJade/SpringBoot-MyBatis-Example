package com.example.demo2;

import com.example.demo2.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao {
    List<User> getAllUsers();
}
