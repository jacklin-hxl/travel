package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;

import java.util.List;

public class RouteDaoImpl implements RouteDao {

    @Override
    public int findTotalCount(int cid) {
        String sql = "select count  "
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        return null;
    }
}
