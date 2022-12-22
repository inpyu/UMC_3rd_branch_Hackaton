package com.example.demo.src.town;

import com.example.demo.src.town.model.DeleteTownReq;
import com.example.demo.src.town.model.GetTownReq;
import com.example.demo.src.town.model.GetTownRes;
import com.example.demo.src.town.model.PatchTownReq;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TownDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 동네 생활 포스트 생성 코드(Create)
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
     * 동네 생활 모든 포스팅 불러오기(Read)
     * */
    public List<GetTownRes> getTowns(){
        String getTownQuery = "select * from TownPoster";
        return this.jdbcTemplate.query(getTownQuery,
                (rs,rowNum) -> new GetTownRes(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("content"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("modifiedAt"))
        );
    }

    /**
     * 동네 생활 포스팅 단일 정보(1개) 불러오기(Read)
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

    public int updateTown(PatchTownReq patchTownReq) {

        String updateTownQuery = "update TownPoster set " +
                "content = ?," +
                "modifiedAt = ? " +
                "where id = ?"; // 해당 idx를 만족하는 Card를 해당 내용으로 변경한다.
        Object[] updateTownParams = new Object[]{
                patchTownReq.getContent(),
                patchTownReq.getModifiedAt(),
                patchTownReq.getId()}; // 주입될 값들(nickname, userIdx) 순
        return this.jdbcTemplate.update(updateTownQuery, updateTownParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
    }

    /**
     * 동네 생활 포스팅 삭제
     * */
    public int deleteTown(DeleteTownReq deleteTownReq) {
        String deletePostQuery = "delete from TownPoster where id = ?";
        Object[] deletePostParams = new Object[]{ deleteTownReq.getId()};

        return this.jdbcTemplate.update(deletePostQuery, deletePostParams);
    }
}
