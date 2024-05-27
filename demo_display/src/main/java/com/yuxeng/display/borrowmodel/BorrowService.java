package com.yuxeng.display.borrowmodel;

import com.yuxeng.display.bookmodel.Dao.BookDao;
import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.usermodel.User;
import com.yuxeng.display.usermodel.UserDao;
import jakarta.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {

  @Resource
  BorrowDao borrowDao;
  @Resource
  UserDao userDao;
  @Resource
  BookDao bookDao;

  Borrow getBorrowById(int id) {
    return borrowDao.selectBorrowById(id);
  }

  /**
   * 使用复合条件查询借阅记录
   *
   * @param userId     借阅用户 Id，null 则全匹配
   * @param bookId     借阅图书 Id，null 则全匹配
   * @param categoryId 借阅图书的分类 Id，null 则全匹配
   * @return 符合条件的所有借阅记录
   */
  List<Borrow> listBorrow(Integer userId, Integer bookId, Integer categoryId) {
    Map<String, Object> map = new HashMap<>();
    map.put("user_id", userId);
    map.put("book_id", bookId);
    map.put("category_id", categoryId);
    return borrowDao.selectBorrow(map, true);
  }

  List<Borrow> listBorrowByBook(int bookId) {
    Map<String, Object> map = new HashMap<>();
    map.put("book_id", bookId);
    return borrowDao.selectBorrow(map, true);
  }

  List<Borrow> listBorrowByUser(int userId) {
    Map<String, Object> map = new HashMap<>();
    map.put("book_id", userId);
    return borrowDao.selectBorrow(map, true);
  }

  List<Borrow> listBorrowByCategory(int categoryId) {
    Map<String, Object> map = new HashMap<>();
    map.put("book_id", categoryId);
    return borrowDao.selectBorrow(map, true);
  }

  List<Borrow> listAllBorrow() {
    Map<String, Object> map = new HashMap<>();
    return borrowDao.selectBorrow(map, true);
  }

  /**
   * 创建借阅记录
   *
   * @param map 请求体内容
   * @return -1：用户不存在，无法借阅；-2：图书不存在，无法借阅； -3：  同种图书已被用户借阅，且未被归还，无法再次借阅； 0：用户已经达到最大借阅数，无法订阅；n：借阅记录的Id
   */
  int saveBorrow(Map<String, Object> map) {
    Integer userId = (Integer) map.get("user_id");
    Integer bookId = (Integer) map.get("book_id");
    User user = userDao.getUserById(userId.longValue());
    Book book = bookDao.selectById(bookId);

    if (user == null) {
      return -1; // 用户不存在，无法借阅
    }
    if (book == null) {
      return -2; // 图书不存在，无法借阅
    }
    if (!borrowDao.selectBorrow(Map.of("user_id", userId, "book_id", bookId),
        false).isEmpty()) {
      return -3; // 同种图书已被用户借阅，且未被归还，无法再次借阅
    }
    if (user.getMax_borrow_books() <=
        borrowDao.countBorrow(Map.of("user_id", userId), false)) {
      return 0; // 用户已经达到最大借阅数，无法订阅
    }
    // 设置借阅日期
    Calendar calendar = Calendar.getInstance();
    Timestamp borrowDate = Timestamp.from(calendar.toInstant());
    // 设置应当归还日期
    calendar.add(Calendar.DATE, user.getMax_borrow_days().intValue());
    Timestamp returnDate = Timestamp.from(calendar.toInstant());

    Borrow borrow = new Borrow();
    borrow.setUser_id(userId);
    borrow.setBook_id(bookId);
    borrow.setBorrow_date(borrowDate);
    borrow.setReturn_date(returnDate);
    borrowDao.insertBorrow(borrow);
    return borrow.getId();
  }

  /**
   * 续借图书
   *
   * @param id 借阅记录 id
   * @return -3：记录不存在；-2：图书已归还，无法续借；-1：逾期； 0：系统错误或无法更新；1：成功续借
   */
  int renewBorrow(int id) {
    Borrow borrow = borrowDao.selectBorrowById(id);
    if (borrow == null) {
      return -3;
    }
    if (borrow.getReally_return_date() != null) {
      return -2; // 图书已归还，无法续借
    }
    // 计算续借时是否逾期
    Calendar calendar = Calendar.getInstance();
    Timestamp curDate = Timestamp.from(calendar.toInstant());
    Timestamp returnDate = borrow.getReturn_date();
    if (curDate.compareTo(returnDate) > 0) {
      return -1;
    }
    // 未逾期则续满
    User user = userDao.getUserById((long) borrow.getUser_id());
    calendar.add(Calendar.DATE, Math.toIntExact(user.getMax_borrow_days()));
    returnDate = Timestamp.from(calendar.toInstant());
    return borrowDao.updateBorrowById(
        Map.of("id", borrow.getId(),
            "return_date", returnDate));
  }

  /**
   * 续借图书
   *
   * @param id 借阅记录 id
   * @return -3：记录不存在；-2：图书已归还；-1：逾期； 0：系统错误或无法更新；1：成功续借
   */
  int returnBorrow(int id) {
    Borrow borrow = borrowDao.selectBorrowById(id);
    if (borrow == null) {
      return -3;
    }
    if (borrow.getReally_return_date() != null) {
      return -2; // 图书已归还，无需再归还
    }
    Calendar calendar = Calendar.getInstance();
    Timestamp curDate = Timestamp.from(calendar.toInstant());
    if (borrow.getReturn_date().compareTo(curDate) < 0) {
      return -1;
    }
    return borrowDao.updateBorrowById(
        Map.of("id", borrow.getId(),
            "really_return_date", curDate));
  }
}
