package cn.song;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyServlet extends HttpServlet {

    /**
     * @Description: 生命周期
     * @author: syq
     * @Date: 2020/2/13
     * @param:
     * @return:
     * @throws：
     */

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


    /**
     * 该方法在web.xml配置项中有初始化参数是需要重写.
     * 可以接收web.xml配置项
     * <init-param>中的初始化参数
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=========================");
        System.out.println("Servlet01...");
        PrintWriter printWriter = resp.getWriter();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        printWriter.write("<h1>"+simpleDateFormat.format(date)+"</h1>");

    }


    @Override
    public void destroy() {
        super.destroy();
    }


}
