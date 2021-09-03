package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pageBean
        PageBean<Route> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setCurrentPage(currentPage);
        //设置单页数据量
        pageBean.setPageSize(pageSize);

        //get totalCount 总数据量 by cid && set
        int totalCount = routeDao.findTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);

        //set totalPage 总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        //获取当前页的数据集合
        List<Route> list = routeDao.findByPage(cid,(currentPage - 1) * pageSize, pageSize,rname);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public Route findOne(int cid, int rid) {
        RouteImgDao routeImgDao = new RouteImgDaoImpl();
        SellerDaoImpl sellerDao = new SellerDaoImpl();

        Route route = routeDao.findOne(cid,rid);
        route.setRouteImgList(routeImgDao.findByRid(rid));
        route.setSeller(sellerDao.findBySid(route.getSid()));

        return route;

    }
}
