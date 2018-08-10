package com.dzq.controller;

import com.dzq.bean.Users;
import com.dzq.service.ServiceFactory;
import com.dzq.service.user.UserService;
import com.dzq.util.Md5Encrypt;
import com.dzq.util.ResultUtil;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class UserServlet extends  BaseServlet {

    //不实例化Servlet层对象 让工厂实例化
    private UserService userService;
    private ResultUtil util=new ResultUtil();
    //当用户访问我们这个Servlet的时候先执行init
    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) ServiceFactory.getServiceImpl("userService");
    }

    @Override
    public Class getServletClass() {
        System.out.println("=====02:UserServlet==>getServletClass");
        return UserServlet.class;
    }

    /**
     * 用户注册的方法
     *
     * @param requset
     * @param response
     * @return
     */
    public String register(HttpServletRequest requset, HttpServletResponse response) {
        //获取用户登录用户名很密码
        String userName = requset.getParameter("username");
        String password = requset.getParameter("password");
        Users users = new Users();
        users.setUserName(userName);
        try {
            users.setPassword(Md5Encrypt.getEncryptedPwd(password));
            System.out.println(users.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
      users.setUserType(0);
        int num=userService.add(users);
        if(num>0){
            return "main";
        }else{
            return "register";
        }
    }


    public String login(HttpServletRequest requset, HttpServletResponse response){
        System.out.println("====>UserSerclet ===>login");
        //获取用户登录用户名很密码
        String userName=requset.getParameter("username");
        String password =requset.getParameter("password");
        //得从数据库中获取一个用户名  如果没有用户名不需要再执行后续代码
        String passwordInDB =userService.validateName(userName);//验用户名是否存在
        if(passwordInDB!=null){//用户名正确并且可以找得到密码
            try {
                if (Md5Encrypt.validPassword(password,passwordInDB)){//验证密码是否正确
                    Users users=userService.login(userName,passwordInDB);
                    //保存对象
                    requset.getSession().setAttribute("loginUser",users);
                    return "main";
                }else{
                    System.out.println("密码错误");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{//用户名错误
            System.out.println("用户名不存在");
        }
        return "login";
    }
    /**
     * ajax验证用户名是否存在
     */
    public ResultUtil validateName(HttpServletRequest requset, HttpServletResponse response){
        //获取用户名
        String userName=requset.getParameter("username");
        //调用Service层代码
        String passwordInDB=userService.validateName(userName);
        if(passwordInDB==null){  //可以注册
            util.resultSuccess();

        }else{
            util.resultFail("该昵称已被占用");
        }
        return  util;
    }
}
