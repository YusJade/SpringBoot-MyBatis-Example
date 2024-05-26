package com.yuxeng.display;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Service.BookService;
import com.yuxeng.display.bookmodel.Service.impl.BookServiceImpl;
import com.yuxeng.display.usermodel.UserService;
import com.yuxeng.display.util.PageBean;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoDisplayApplicationTests {

  @Resource
  UserService userService;
  @Resource
  BookServiceImpl bookService;

  @Test
  void contextLoads() {
    Map<String, Object> map = new HashMap<>();
    map.put("pageOn", 1);
    map.put("pageSize", 10);
    PageBean<Book> res = bookService.listBooksByPage(map);
    System.out.println(res.getDatas().size());
  }

}
