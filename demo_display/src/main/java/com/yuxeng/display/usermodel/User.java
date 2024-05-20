package com.yuxeng.display.usermodel;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String real_name;
    private String gender;
    private String phone_number;
    private String email;
    private String max_borrow_days;
    private String max_borrow_books;
    private Timestamp created_at;
}
