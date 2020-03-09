package cn.song.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 一个检查登录状态的过滤器
 * @author: syq
 * @Date: 2020/3/8
 * @param:
 * @return:
 * @throws：
 */

public class LogInFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            //未登录
            response.sendRedirect("index.jsp");
            return;
        }else{
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
