package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteService {

    public Boolean findByUidAndRid(int uid, int rid);

    public int findCountByRid(int rid);

    public int addFavorite(int uid,int rid);
}
