package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{

    private final CategoryServiceImpl categoryService =  new CategoryServiceImpl();
    
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询数据
        long st = System.currentTimeMillis();
        List<Category> cs = categoryService.findAll();
        long et = System.currentTimeMillis();
        System.out.println("hxl: " + (et - st) + "ms");
        resp.setContentType("application/json;charset=utf-8");
        writeValue(cs,resp);
    }

}
