package com.company.thread;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        System.out.println("Servlet01...");
    }

    /**
     * 该方法在web.xml配置项中有初始化参数是需要重写.
     * 可以接收web.xml配置项
     * <init-param>中的初始化参数
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 得到初始化参数
        String encode = config.getInitParameter("encode");
        System.out.println("编码：" + encode);
    }

}
