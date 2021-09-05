package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    public Favorite findByUidAndRid(int uid, int rid);

    public int findCountByRid(int rid);

    public int addByUidAndRid(int uid, int rid);
}
