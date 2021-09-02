package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteServiceImpl routeService = new RouteServiceImpl();

    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get parameter
//        req.setCharacterEncoding("utf-8");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");
        String rname = req.getParameter("rname");
        if (rname != null && rname.length() > 0 ) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }



        //处理参数
        int cid = 0;//类别id
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
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

        //调用service查询pagebean对象
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);

        resp.setContentType("application/json;charset=utf-8");
        writeValue(pageBean, resp);

    }
}
