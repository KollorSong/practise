package cn.song.servlets;

import cn.song.utils.CpachaUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class CpachaServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 8341377811482809071L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("method");
        if (path.equals("loginCapcha")){
            generateLoginCode(req,resp);
        }else {
            resp.getWriter().write("刷新失败");
        }
    }

    public void generateLoginCode(HttpServletRequest req, HttpServletResponse resp){
        CpachaUtil cpachaUtil = new CpachaUtil();
        String generatorVCode = cpachaUtil.generatorVCode();
        req.getSession().setAttribute("loginCapcha",generatorVCode);
        BufferedImage bufferedImage = cpachaUtil.generatorVCodeImage(generatorVCode,true);
        try {
            ImageIO.write(bufferedImage,"gif",resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
