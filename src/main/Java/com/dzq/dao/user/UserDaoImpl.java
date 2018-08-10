package com.dzq.dao.user;

import com.dzq.bean.Users;
import com.dzq.util.BaseDao;
import com.dzq.util.PageUtil;
import com.dzq.util.ResultSetUtil;
import com.dzq.util.ResultUtil;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * 真正的增删改操作
 */
public class UserDaoImpl extends BaseDao implements UserDao{
    /**
     * 注册功能
     * @param users
     * @return
     */
    @Override
    public int add(Users users) {
        String sql="INSERT INTO`user` (`username`,`password`,`userType`) VALUE(?,?,?)";
       Object[] params ={users.getUserName(),users.getPassword(),users.getUserType()};
        return executeUpdate(sql,params);
    }
    /**
     * 验证用户名是否存在的操作/密码是否正确
     * @param username
     * @return
     */
    @Override
    public String validateName(String username) {
        String sql="SELECT `password` FROM `user` WHERE `username`=?";
        rs=executeQuery(sql,username);
        String password=null;
        try {
            if(rs.next()){
                    password=rs.getString("password");
                }
                }catch (Exception e) {
            e.printStackTrace();
            }
        return password;
    }

    /**
     * 登录
     */
    @Override
    public Users login(String username, String password) {
        String sql="SELECT `id`,`username`,`password`,`userType`FROM `user` WHERE `username`=? AND `password`=?";
        Object [] params={username,password};
        rs=executeQuery(sql,params);
        Users users;
        System.out.println("sssssss");
        users = ResultSetUtil.eachOne(rs,Users.class);
        return users;
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


}
