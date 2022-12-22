package com.example.demo.src.profile;


import com.example.demo.src.profile.model.GetProfileRes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDao {

    private JdbcTemplate jdbcTemplate;

    public GetProfileRes getProfileUser(int id){
        String getProfileQuery = "select * from User where id = ?";
        int getProfileParams = id;
        return this.jdbcTemplate.queryForObject(getProfileQuery,
                (rs,rowNum) -> new GetProfileRes(
                        rs.getInt("id"),
                        rs.getString("nickName"),
                        rs.getFloat("manner"),
                        rs.getString("town")),
                getProfileParams);
    }

}
