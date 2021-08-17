package cn.itcast.travel.service.impl;


import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断用户是否存在
        if (u != null){
            return false;
        }
        //2.保存用户信息
        //3.设置激活码，唯一字符串
        //4.设置激活状态
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);
        String content;
        content = String.format(
                "<a href='http://localhost/travel/activeUserServlet?code=%s' >点击激活</a>",
                user.getCode());
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    public boolean active(String code) {
        //判断uuid对应的用户是否存在
        User user = userDao.findByCode(code);
        if (user != null){
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }
}
