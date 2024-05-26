package com.yuxeng.display.bookmodel.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.BookService;
import com.yuxeng.display.util.PageBean;
import jakarta.servlet.http.HttpSession;
import com.yuxeng.display.util.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookService bookService;

  // 显示全部书籍
  @RequestMapping(value = "/listBook")
  @ResponseBody
  public String queryBook(@RequestParam(value = "page", defaultValue = "1") Integer pageOn,
      @RequestParam(value = "limit", defaultValue = "5") Integer pageSize,
      String bookName,String author,String categoryId, HttpSession session
  ) {
    // 创建一个Map对象，用于存储分页和查询参数
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("pageOn",pageOn);
    paramMap.put("pageSize",pageSize);

    // 判断查询条件是否为空
    if (StringUtil.isNotEmpty(bookName)) paramMap.put("bookName",bookName);
    if (StringUtil.isNotEmpty(author)) paramMap.put("author", author);
    if (StringUtil.isNotEmpty(categoryId)) paramMap.put("categoryId", Integer.parseInt(categoryId));

    // 查询分页图书数据
    PageBean<Book> pageBean = bookService.listBooksByPage(paramMap);

    // 获取类别
    List<BookCategory> categoryList = bookService.listCategory();
    String category = "category";
    session.setAttribute(category,categoryList);

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode obj = mapper.createObjectNode();

    obj.put("code",0);
    obj.put("message","");
    obj.put("count",pageBean.getTotalPage());
    obj.putPOJO("data", pageBean.getDatas());
    return obj.toString();
  }
}
