package com.dzq.controller;

import com.dzq.util.ResultUtil;
import com.mysql.jdbc.util.ResultSetUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class UserServlet extends  BaseServlet{

    @Override
    public Class getServletClass() {
        System.out.println("=====02:UserServlet==>getServletClass");
        return UserServlet.class;
    }
    public ResultUtil login(HttpServletRequest requset, HttpServletResponse response){
        System.out.println("====>UserSerclet ===>login");
        //获取用户登录用户名很密码
        String userName=requset.getParameter("userName");
        String password =requset.getParameter("password");
        ResultUtil util=new ResultUtil();
        //得从数据库中获取一个用户名  如果没有用户名不需要再执行后续代码
        if(userName.equals("admin")){
            util.resultSuccess(userName);
        }else{
            util.resultFail("用户名错误");
        }
return  util;
    }
}
