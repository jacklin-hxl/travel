package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByUidAndRid(int uid, int rid) {
        Favorite favorite = null;
        String sql = "select * from tab_favorite where uid = ? and rid = ?";

        try {
            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, rid);
        }catch (EmptyResultDataAccessException e) {
            System.out.println(sql);
        }
        return favorite;

    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public int addByUidAndRid(int uid, int rid) {
        String sql = "insert into tab_favorite values(?, ?, ?)";
        return template.update(sql, rid, new Date(), uid);
    }


}

