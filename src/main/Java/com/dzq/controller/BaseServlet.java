package com.dzq.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * BaseServlet是所有的Servlet的父类
 * 这个类写的是什么
 * 所有的请求都会进入此类
 * 获取请求，根据请求分别发到各个Servlet
 * 我们是怎么知道用户访问的是哪个Servlet
 * 就算我们找到了 Servlet还要确定执行那个方法
 * 方法返回值的类型
 *    01：Json类型的数据
 *    02：普通字符串=》页面名称
 * 返回方式：
 *   01：转发
 *   02：重定向
 *   03：不返回==》ajax
 */

public abstract class BaseServlet extends HttpServlet {
    /*
     *因为所有的子Servlet都要继承这个BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
  public abstract Class getServletClass();
    @Override
    protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
//获取用户的请求 请求中必须要携带一个参数 方法名称 methodName
        String methodName = requset.getParameter("methodName");
        System.out.println("======先进入");
        //根据用户传递参数 确定了执行那个子Servlet中的这个methodName方法
        //用户需要指定的方法
        Method method = null;
        //执行方法的返回值
        Object result = null;
        System.out.println(methodName+"-------------");
        if (methodName == null || "".equals(methodName)) {
            result = execute(requset, response);//统一返回到主页面
        } else {//证明有方法 先确定是哪个Servlet
                //找到方法
                try {
                    System.out.println("ssssssss");
                    method = getServletClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                    result = method.invoke(this, requset, response);
                    System.out.println("=====》获取了需要返回的页面+result");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //根据不同的返回值，执行不同的操作
            thView (requset,response, result);


    }

    private void thView(HttpServletRequest requset, HttpServletResponse response, Object result) throws ServletException, IOException {
    if(result==null){
        System.out.println("====没有返回值");
    }else{//要么Json要么字符串
        if(result instanceof  String ){
            String viewName=result.toString()+".jsp";
            System.out.println("最终的跳转页面====》"+viewName);
            requset.getRequestDispatcher(viewName).forward(requset,response);
        }else{
            //返回的是Json
            System.out.println("=======Json数据的处理");
            String resultJson =(String ) JSON.toJSONString(result) ;
            System.out.println("json====="+resultJson);
            PrintWriter writer=response.getWriter();
            writer.write(resultJson);
            writer.flush();
            writer.close();
        }
    }
    }

    private Object execute(HttpServletRequest requset, HttpServletResponse response) {
  return "main";
   }
}
