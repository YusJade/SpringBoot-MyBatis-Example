package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

  @Resource
  UserDao userDao;
  @Resource
  Config cfg;

  /**
   * 登陆检测
   *
   * @param username 用户名
   * @param password 密码
   * @return String / boolean  // 是否需要提供哪个填写出问题了 MARK
   */
  public String validateUser(String username, String password) {
    User db_user = userDao.getUserByUsername(username);
    if (db_user == null) {
      return "账号输入错误";  // TODO:是否需要转为json-->Controller层再写 | 将错误信息统一管理
    }

    if (!password.equals(db_user.getPassword())) {
      return "密码输入错误";
    }

    return "成功";
  }

  /**
   * 用户注册
   *
   * @param username String-昵称
   * @param password String-密码
   * @param name     String-真实姓名
   * @param gender   String-性别
   * @param phone    String-手机号
   * @param email    String-邮箱
   * @return boolean
   */
  public boolean registerUser(String username, String password, String name, String gender,
      String phone, String email) {
    // 最大借阅日和最大借书量为默认值，仅选项仅管理员可更改

    // TODO:检测username、password、gender、phone、email等信息的合规性

    // TODO:insertUser在失败时会报什么类型的错
    try {
      int db_id = userDao.insertUser(username, password, name, gender, phone, email,
          cfg.max_borrow_days,
          cfg.max_borrow_books);
      // DEBUG
      System.out.println("Insert DB_ID : " + db_id);

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean resetPassword(String username) {
    // TODO：查询登陆状态——应该在Controller里查询/写重载函数
    // TODO：如果未登录，要使用什么来验证
    // TODO：邮箱
     String Firefly =
         """
             @@@@@@@@@@@@@@@@@@@@###%@@@@@@@@@@@@%%@%@%####*#@@@@@@@@@@@@
             @@@@@@@@@@@@@@@@@%#*#%@@@@@@@@@@@@@@@@@@@%%@@@%#**%@@@@@@@@@
             @@@@@@@@@@@@@@@%**%@@@@@@@*+#*#*====++**##%@@@@@@%#+%@@@@@@@
             @@@@@%%@@%@@@@*+%@@%@@@%*--*#***=--::::---==*@@@@%@%+#@@@@@@
             @@%%@%#%%%@@%+*@@@@%@@@%%%@@@%%@%###**+==--::-%@@@%%@+#@@@@@
             @@@%%@@@@%@#=#@@@@@@@@@@@@@@%@@@@@@@@@@@@%#*+-:#@@@#@@=#@@@@
             @@@@%%@%%%*+*@@@@@@@@%%%%%%#@@@%@@@@@@%@@@@@@@%*%@@@#%*#*#@@
             @@@@%@@%@%+*%@@@@@@%@@@@@%#@@%@@@%%@@@%@%%%@@@@@%@@@%#*%@*#%
             @@@@@@@@@=**%#@@@@##@%%#%%%@@@@@@@@@@%@%%%%%%@@@@@@@@*#@%%#%
             @@@@@@@@%=#+**@@@%+##%%@@%%@@@@@@@@@%%@@@@@@%@@@@@@@@*%@@@@%
             @@@@@@@@*+#+++#@@##@@@@@@@%%%@@@@%@%%@%%@@@@@@@@@@@@@**%##+#
             @@@@@@@@++*#%@+*%%=:+*#%#%%@%%@%%#%%@@@%#%@@#@@@@@@@@*-++=-%
             @@@@@%###%@@@%#%%%#*:--==*@@@%%%%##%%@@@@%%*#@@@@@@@%#--=++@
             @@#*#%@%@@@@%+*@%%%@@@@@@@@@@@@@@@#-====+=+#@@@@@@@@+#=::=:#
             %*#@@@*#@@@#+*+#%%###%@@@@%%%%@@@@@@%#*=*#*%@@@@@@@+**+--*%@
             +@@@#*+%%%%**##**@%%%%##%%%%%%%@@@@@%%@%%#%@@@@@#@#++**=-:*@
             @@@**%@@@@@@@@@@#%@@@@#*@%%%%%%%%%%###%%###@@@%*+#=*+*=@%--@
             #@#*@@@%%%%%%%%%%++****%@@@@@@@@*%@@%%##@%#+=+*+*++%*+@@@@@@
             #+#@@@%%#@@@@@@@%##%%#%%@%%%%%%@#%%@@@%=+==+*##**+*@@*@@@@@@
             +#@@@%%%@@@@@@@@%%%%%%@@@@@@@@%%%####*+*#*+++**+**%@@@*@@@@@
             %@@@#%#%@@@@@@@%%%%%#%@@%@@@@%%%%%%%#%%#**###*=+**@@@@@#%@@@
             @@@%%%%@@@@@@@%%%%%#%@@@@@@@%%%%%%%%%@@%#%%%@@@#+=@@@@@@##@@
             @@%%%%@@@@@@@@%%%%%%@@@@@@@%%%%%%%#%@@@@%@@%#%@@@**@@@@@@#@@
             @%#%#%@@@@@@@%%%%%%@@@@@@@@%%%%%%%#@@@@@@@@%@#%@@@*%@@@@%*@@
             %#%#%@@@%@@@%%%%%#%@@%@@@@%%%%%%%%%@@@@@@@%%%#@@@%*@@@%**@@@
             %%%%@@@@@@@%%%%%%%@@@@@@@%%%%%%%%%@@@@@@@%%%#%@@@*#%###%@@@@
             %%%@@@@@@@@%%%%%%@@@@@@@@%%%%%%%#@@@@%@@@%%%#@@@@+%%%#@@%@@@
             %#%@@@@@@@%%%%%#%@@%@@@@%%%%%%%%%@@%%@@@%%%#%@@@*#@%%#%%%@@@
             %%@@@@@@@%%%%%#%@@@@@@@%%%%%%%%%@%%#%@@%%%##@@@%*@@@%%%%#%@@
             %@@@@%@@@%%%%%%@@@@@@@%%%%%%%###%%##@@@%%%#@@@@+%@@@%@@@@@@@
         """;

     return true;
  }

  public String resetPassword(String username, String new_password) {
    // TODO: 加入新密码检测机制
    try {
      int update_record = userDao.updateUserPasswordByUsername(username, new_password);

      if (update_record > 0) {
        return "修改成功";
      } else {
        return "修改失败";
      }
    } catch (Exception e) {
      return "输入规范点，检测机制没这么智能：（";
    }
  }
}