package com.example.demo2.demos.web;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao {
    List<User> getAllUsers();
}
