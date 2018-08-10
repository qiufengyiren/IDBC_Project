package com.dzq.service.user;

import com.dzq.bean.Users;
import com.dzq.dao.user.UserDao;
import com.dzq.dao.user.UserDaoImpl;
import com.dzq.util.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 在不改变dao层代码的前提下，做一些业务逻辑！
 * 增强处理==》增加系统级业务
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

     @Override
     public int add(Users users) {
          return userDao.add(users);
     }

     @Override
     public int deleteByCondition(Serializable id) {
          return 0;
     }

     @Override
     public int update(Users users) {
          return 0;
     }

     @Override
     public Users findByCondition(Serializable id) {
          return null;
     }

     @Override
     public List<Users> findAll() {
          return null;
     }

     @Override
     public int findRownum() {
          return 0;
     }

     @Override
     public List<Users> findAllByPage(PageUtil util, Object... params) {
          return null;
     }

    /**
     * 验证用户名是否存在
     */
    @Override
    public String validateName(String username) {
        return userDao.validateName(username);
    }

    /**
     * 登陆操作
     */
    @Override
    public Users login(String username, String password) {
        return userDao.login(username,password);
    }
}
