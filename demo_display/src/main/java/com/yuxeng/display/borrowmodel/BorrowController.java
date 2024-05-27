package com.yuxeng.display.borrowmodel;

import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/borrow")
@RestController
public class BorrowController {

  @Resource
  BorrowService borrowService;

  @GetMapping("/all-history")
  Responses<List<Borrow>> queryAllBorrow() {
    return new Responses<>(ResponseCode.SUCCESS, "借阅记录查询成功",
        borrowService.listAllBorrow());
  }

  @GetMapping("/history")
  Responses<List<Borrow>> queryBorrow(
      @RequestParam(value = "user_id", required = false) Integer userId,
      @RequestParam(value = "book_id", required = false) Integer bookId,
      @RequestParam(value = "category_id" ,required = false) Integer categoryId) {
    return new Responses<>(ResponseCode.SUCCESS, "借阅记录查询成功",
        borrowService.listBorrow(userId, bookId, categoryId));
  }


  @PostMapping
  void addBorrow(Map<String, Object> borrow) {
    // TODO: 对于同一本书，在未归还前，用户不能再次借阅
  }

  @DeleteMapping("/return")
  void returnBorrow(int id) {

  }

  @PutMapping("/renew")
  void renewBorrow() {

  }

}
