package com.example.demo.src.town;

import com.example.demo.src.town.model.GetTownReq;
import com.example.demo.src.town.model.GetTownRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TownDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 명함 생성 코드(Create)
     * */
    public int createTownPost(GetTownReq getTownReq) {
        String createTownPostQuery = "insert into TownPoster" +
                " (`userId`," +
                " content," +
                " createdAt," +
                " modifiedAt) " +
                "VALUES (?,?,?,?)"; // 실행될 동적 쿼리문

        Object[] createTownPostParams = new Object[]{
                getTownReq.getUserId(),
                getTownReq.getContent(),
                getTownReq.getCreatedAt(),
                getTownReq.getModifiedAt()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createTownPostQuery, createTownPostParams);

        String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 postId 번호를 반환한다.
    }


    /**
     * 마을 포스팅 단일 정보(1개) 불러오기(Read)
     * */
    public GetTownRes getTown(int townPosterId){
        String getTownImgQuery = "select * from TownPoster where id = ?";
        int getTownImgParams = townPosterId;
        return this.jdbcTemplate.queryForObject(getTownImgQuery,
                (rs,rowNum) -> new GetTownRes(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("content"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("modifiedAt")),
                getTownImgParams);
    }

}
