package com.yuxeng.display;

import com.yuxeng.display.usermodel.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoDisplayApplicationTests {

  @Resource
  UserService userService;

  @Test
  void contextLoads() {
  }

}
