package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

public class RouteServiceImpl implements RouteService {
    private
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        //封装pageBean
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

    }
}
