package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String code = request.getParameter("code");
        if(code != null){
            UserServiceImpl service = new UserServiceImpl();
            boolean flag = service.active(code);

            String msg = null;

            if (flag){
                //active successful
                msg = "激活成功，请<a href='login.html' >登录</a>";
            }else {
                //active failed
                msg = "激活失败，请联系管理员!";
            }

            response.setContentType("text/html;charset=utf-8");

            try {
                response.getWriter().write(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        this.doPost(request, response);
    }
}
