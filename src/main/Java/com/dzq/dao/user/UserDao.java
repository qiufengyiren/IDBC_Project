package com.dzq.dao.user;

import com.dzq.bean.Users;
import com.dzq.dao.IBaseDao;

public interface UserDao extends IBaseDao<Users> {

/**
 * 验证用户名的操作
 */
String validateName(String username);

Users login (String username,String password);
}
