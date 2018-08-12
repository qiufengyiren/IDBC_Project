package com.dzq.controller;

import com.dzq.bean.Users;
import com.dzq.service.ServiceFactory;
import com.dzq.service.user.UserService;
import com.dzq.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/home")
public class HomeServlet  extends  BaseServlet{

    private UserService userService;

    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }
    //当用户访问我们这个servlrtt的时候 ，先执行init

    @Override
    public void init() throws ServletException {
        userService=(UserService) ServiceFactory.getServiceImpl("userService");
    }
    /**
     * 分页的方法
     */
    public PageUtil findAllByPage(HttpServletRequest request, HttpServletResponse response){
        //获取当前页面 pageIndex
        int pageIndex =Integer.parseInt(request.getParameter("pageIndex"));
        //创建PageUtil对象
        PageUtil pageUtil=new PageUtil();
        //把获取的数据封装到PageUtil里面
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotalCount(userService.findRownum());
        //调用service代码 获取数据
        List<Users> list=userService.findAllByPage(pageUtil);
        pageUtil.setList(list);
        //返回pageUtil
        return pageUtil;
    }
    public String deleteUser(HttpServletRequest request, HttpServletResponse response){
        String id= request.getParameter("id");
        int num= userService.deleteByCondition(id);
        if (num>0){
            return "main";
        }
        return "login";
    }
}
