package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    protected void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");

        int cid = 0;//类别id
        //处理参数
        if (cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 1;//当前页面，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 5; //每页显示条数，默认五条
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
    }
}
