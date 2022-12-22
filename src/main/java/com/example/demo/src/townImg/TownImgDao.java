package com.example.demo.src.townImg;

import com.example.demo.src.townImg.model.GetTownImgRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TownImgDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetTownImgRes getTownImage(int townPosterId){
        String getTownImgQuery = "select * from TownPosterImg where townPosterId = ?";
        int getTownImgParams = townPosterId;
        return this.jdbcTemplate.queryForObject(getTownImgQuery,
                (rs,rowNum) -> new GetTownImgRes(
                        rs.getInt("id"),
                        rs.getString("storedUrl"),
                        rs.getInt("townPosterId"),
                        rs.getString("originalFileName")),
                getTownImgParams);
    }

}
