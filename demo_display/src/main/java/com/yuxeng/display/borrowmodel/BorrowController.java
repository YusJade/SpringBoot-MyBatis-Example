package com.yuxeng.display.borrowmodel;

import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
=======
>>>>>>> serendipity
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/borrow")
@RestController
public class BorrowController {

  @Resource
  BorrowService borrowService;

//  @GetMapping("/all-history")
//  Responses<List<Borrow>> queryAllBorrow() {
//    return new Responses<>(ResponseCode.SUCCESS, "借阅记录查询成功",
//        borrowService.listAllBorrow());
//  }

  /**
   * 多条件查询借阅记录
   *
   * @param userId     用户 Id
   * @param bookId     图书 Id
   * @param categoryId 图书分类 Id
   * @return 符合条件的所有借阅记录
   */
  @GetMapping
  Responses<List<Borrow>> queryBorrow(
      @RequestParam(value = "user_id", required = false) Integer userId,
      @RequestParam(value = "book_id", required = false) Integer bookId,
<<<<<<< HEAD
      @RequestParam(value = "category_id", required = false) Integer categoryId,
      @RequestParam(value = "contain_finished", required = false) Boolean isContainFinished) {
    return new Responses<>(ResponseCode.SUCCESS, "借阅记录查询成功",
        borrowService.listBorrow(userId, bookId, categoryId, isContainFinished));
=======
      @RequestParam(value = "category_id", required = false) Integer categoryId) {
    return new Responses<>(ResponseCode.SUCCESS, "借阅记录查询成功",
        borrowService.listBorrow(userId, bookId, categoryId));
>>>>>>> serendipity
  }


  @PostMapping
  Responses<Borrow> addBorrow(@RequestBody Map<String, Object> map) {
    int res = borrowService.saveBorrow(map);
    return switch (res) {
<<<<<<< HEAD
      case -4 -> new Responses<>(ResponseCode.BOOK_UNAVAILABLE, "图书库存不足", null);
=======
>>>>>>> serendipity
      case -3 -> new Responses<>(ResponseCode.BORROW_ALREADY, "无法重复借阅同一本图书", null);
      case -2 -> new Responses<>(ResponseCode.BOOK_NOT_EXIST, "图书不存在", null);
      case -1 -> new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "用户不存在", null);
      case 0 -> new Responses<>(ResponseCode.BORROW_LIMITED, "已达到最大借阅数", null);
      default ->
          new Responses<>(ResponseCode.SUCCESS, "借阅成功", borrowService.getBorrowById(res));
    };
  }

  @PutMapping("/{id}/return-book")
  Responses<Borrow> returnBorrow(@PathVariable int id) {
    return switch (borrowService.returnBorrow(id)) {
      case -3 -> new Responses<>(ResponseCode.BORROW_NOT_EXIST, "借阅记录不存在", null);
      case -2 -> new Responses<>(ResponseCode.RETURN_ALREADY, "已归还，请勿重复归还",
          borrowService.getBorrowById(id));
      case -1 -> new Responses<>(ResponseCode.RETURN_OVERDUE, "逾期归还",
          borrowService.getBorrowById(id));
      case 0 -> new Responses<>(ResponseCode.FAILED, "系统错误", null);
      default -> new Responses<>(ResponseCode.SUCCESS, "图书归还成功",
          borrowService.getBorrowById(id));
    };
  }

  @PutMapping("/{id}/renew-book")
  Responses<Borrow> renewBorrow(@PathVariable int id) {
    return switch (borrowService.renewBorrow(id)) {
      case -3 -> new Responses<>(ResponseCode.BORROW_NOT_EXIST, "借阅记录不存在", null);
      case -2 -> new Responses<>(ResponseCode.RETURN_ALREADY, "已归还，无法续借", null);
      case -1 -> new Responses<>(ResponseCode.RETURN_OVERDUE, "借阅已逾期，请先归还图书",
          borrowService.getBorrowById(id));
      case 0 -> new Responses<>(ResponseCode.FAILED, "系统错误", null);
      default -> new Responses<>(ResponseCode.SUCCESS, "图书续借成功",
          borrowService.getBorrowById(id));
    };
  }

}
