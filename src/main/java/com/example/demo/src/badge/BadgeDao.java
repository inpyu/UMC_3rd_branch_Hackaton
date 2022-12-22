package com.example.demo.src.badge;

import com.example.demo.src.badge.model.GetBadgeRes;
import com.example.demo.src.profile.model.GetProfileRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class BadgeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetBadgeRes getBadge(int id){
        String getBadgeQuery = "select * from Badge where userId = ?";
        int getBadgeParams = id;
        return this.jdbcTemplate.queryForObject(getBadgeQuery,
                (rs,rowNum) -> new GetBadgeRes(
                        rs.getInt("userId"),
                        rs.getBoolean("startDeal"),
                        rs.getBoolean("townPioneer"),
                        rs.getBoolean("startItem"),
                        rs.getBoolean("firstRecommend"),
                        rs.getBoolean("startWarm")),
                getBadgeParams);
    }

}
