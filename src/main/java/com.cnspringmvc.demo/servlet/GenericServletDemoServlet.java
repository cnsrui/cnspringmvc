package com.cnspringmvc.demo.servlet;
import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 继承 GenericServlet，就不需要重写不需要用到的Servlet方法了
 * urlPatterns 必须以 / 开头
 */
@WebServlet(name = "GenericServletDemoServlet", 
    urlPatterns = { "/generic" },
    initParams = {
        @WebInitParam(name="admin", value="Harry Taciak"),
        @WebInitParam(name="email", value="admin@example.com")
    }
)
public class GenericServletDemoServlet extends GenericServlet {
    
    private static final long serialVersionUID = 62500890L;

    @Override
    public void service(ServletRequest request, 
            ServletResponse response)
            throws ServletException, IOException {

        ServletConfig servletConfig = getServletConfig();
        String admin = servletConfig.getInitParameter("admin");
        String email = servletConfig.getInitParameter("email");

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.print("<html><head></head><body>" + 
                "Admin:" + admin + 
                "<br/>Email:" + email +
                "</body></html>");
    }
}