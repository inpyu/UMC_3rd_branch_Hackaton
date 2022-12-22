package com.example.demo.src.town;

import com.example.demo.src.town.model.GetTownImgReq;
import com.example.demo.src.town.model.GetTownImgRes;
import com.example.demo.src.town.model.GetTownReq;
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

    /**
     * 동네 생활 이미지 생성 코드(Create)
     * */
    public int createTownPost(GetTownImgReq getTownImgReq) {
        String createTownPostImgQuery = "insert into TownPosterImg" +
                " ( storedUrl," +
                " townPosterId," +
                " originalFileName) " +
                "VALUES (?,?,?)"; // 실행될 동적 쿼리문

        Object[] createTownPostImgParams = new Object[]{
                getTownImgReq.getStoredUrl(),
                getTownImgReq.getTownPosterId(),
                getTownImgReq.getOriginalFileName()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createTownPostImgQuery, createTownPostImgParams);

        String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 postImgId 번호를 반환한다.
    }

    /**
     * 동네 생활 조회 코드
     * */
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
