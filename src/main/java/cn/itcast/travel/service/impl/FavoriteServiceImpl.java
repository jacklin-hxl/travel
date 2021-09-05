package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();

    @Override
    public Boolean findByUidAndRid(int uid, int rid) {

        Favorite favorite = favoriteDao.findByUidAndRid(uid, rid);

        if (favorite != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int findCountByRid(int rid) {
        return favoriteDao.findCountByRid(rid);
    }

    @Override
    public int addFavorite(int uid, int rid) {
        return favoriteDao.addByUidAndRid(uid, rid);
    }

}
