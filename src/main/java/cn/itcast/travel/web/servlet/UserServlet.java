package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        //将info对象序列化json
        ResultInfo info = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        String json = null;

        //获取验证码
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");    //为了保证验证码只能使用一次

        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

            try {
                json = mapper.writeValueAsString(info);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            try {
                assert json != null;
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);

        //4.响应结果
        if (flag){
            //注册成功
            info.setFlag(true);
        }
        else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }

        try {
            json = mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            //将json数据写回客户端
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");

        try {
            mapper.writeValue(response.getOutputStream(),user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession().invalidate();

        try {
            response.sendRedirect(request.getContextPath() + "/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException {
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

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.login(user);

        ResultInfo info = new ResultInfo();

        //判断用户对象是否为null
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }

        if (u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("用户未激活");
        }

        if (u != null && "Y".equals(u.getStatus())){
            info.setFlag(true);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");

        try {
            mapper.writeValue(response.getOutputStream(),info);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
