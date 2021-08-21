package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{

    private final CategoryServiceImpl categoryService =  new CategoryServiceImpl();
    
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询数据
        List<Category> cs = categoryService.findAll();
    }

}
