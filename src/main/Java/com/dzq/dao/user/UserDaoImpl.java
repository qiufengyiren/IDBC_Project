package com.dzq.dao.user;

import com.dzq.bean.Users;
import com.dzq.util.BaseDao;
import com.dzq.util.PageUtil;

import java.io.Serializable;
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
