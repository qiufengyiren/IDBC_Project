package com.dzq.dao.user;

import com.dzq.bean.Users;
import com.dzq.util.BaseDao;
import com.dzq.util.PageUtil;
import com.dzq.util.ResultSetUtil;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * 真正的增删改操作
 */
public class UserDaoImpl extends BaseDao implements UserDao{
    /**
     * 注册功能
     */
    @Override
    public int add(Users users) {
        String sql="INSERT INTO `user` (`username`,`password`,`userType`,`file`) VALUES (?,?,?,?)";
       Object[] params ={users.getUserName(),users.getPassword(),users.getUserType(),users.getFile()};
        return executeUpdate(sql,params);
    }
    /**
     * 验证用户名是否存在的操作/密码是否正确
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
        String sql="SELECT `id`,`username`,`password`,`userType`,`file` FROM `user` WHERE `username`=? AND `password`=?";
        Object [] params={username,password};
        rs=executeQuery(sql,params);
        Users users;
        System.out.println("sssssss");
        users = ResultSetUtil.eachOne(rs,Users.class);
        return users;
    }
    /**
     * 查询总记录数
     */
    @Override
    public int findRownum() {
        String sql="SELECT COUNT(1) AS COUNT FROM `user`";
        rs=executeQuery(sql);
        int count=0;
        try {
            if(rs.next()){
                count=rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     * 删除记录数
     */
    @Override
    public int deleteByCondition(Serializable id) {
        String sql="DELETE FROM `user` WHERE id=?";
        int num=executeUpdate(sql,id);
        return num;
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



    /**
     *
     * （当前页-1）*pageSize
     *   limit  ?,2
     */
    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        String sql="SELECT `id` AS `id`,`username`,`password`,`userType`,`file` FROM `user` LIMIT ?,?";
        Object[] objects={(util.getPageIndex()-1)*util.getPageSize(),util.getPageSize()};
        rs=executeQuery(sql,objects);
        List<Users> list=ResultSetUtil.eachList(rs,Users.class);
        return list;
    }


}
