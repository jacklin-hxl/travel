package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category/*")
public class categoryServlet extends BaseServlet{

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("category-add");
    }

    public void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("category-搜索");
    }
}
