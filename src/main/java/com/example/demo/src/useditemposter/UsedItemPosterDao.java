package com.example.demo.src.useditemposter;

import com.example.demo.src.useditemposter.model.GetUsedItemPosterRes;
import com.example.demo.src.useditemposter.model.PostUsedItemPosterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UsedItemPosterDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetUsedItemPosterRes getUsedItemPoster(int usedItemPosterId) {
        String getUsedItemPosterQuery = "SELECT * FROM UsedItemPoster p" +
                " JOIN Category c on c.id =  p.categoryId  where p.id = ?";
        int getUsedItemPosterParam = usedItemPosterId;

        return this.jdbcTemplate.queryForObject(getUsedItemPosterQuery,
                (rs, rowNum) -> new GetUsedItemPosterRes(
                        rs.getInt("id"),
                        rs.getString("c.name"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("status"),
                        rs.getString("content"),
                        rs.getInt("hearts"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("modifiedAt")
                ),getUsedItemPosterParam);


    }

    public int createUsedItemPoster(PostUsedItemPosterReq request) {
        String createUsedItemPosterQuery = "INSERT INTO UsedItemPoster (sellerId, categoryId, title, price, content) VALUES " +
                "(?,?,?,?,?)";
        Object[] createUsedItemPosterParams = new Object[] {
                request.getSellerId(),
                request.getCategoryId(),
                request.getTitle(),
                request.getPrice(),
                request.getContent()
        };

        this.jdbcTemplate.update(createUsedItemPosterQuery,createUsedItemPosterParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetUsedItemPosterRes> findByCategoryId(int categoryId ,int size ,int offset) {
        String getPostersByCategoryQuery = "SELECT * FROM UsedItemPoster p JOIN Category c ON c.id = p.categoryId where p.categoryId = ? " +
                "ORDER BY p.id DESC LIMIT ? OFFSET ?";

        Object[] getByCategoryParams = new Object[]{categoryId, size, offset};

        return this.jdbcTemplate.query(getPostersByCategoryQuery,
                (rs, rowNum) -> new GetUsedItemPosterRes(
                        rs.getInt("id"),
                        rs.getString("c.name"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("status"),
                        rs.getString("content"),
                        rs.getInt("hearts"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("modifiedAt")
                ), getByCategoryParams);
    }

    public List<GetUsedItemPosterRes> findAll(int size ,int offset) {
        String getPostersByCategoryQuery = "SELECT * FROM UsedItemPoster p JOIN Category c ON c.id = p.categoryId " +
                "ORDER BY p.id DESC LIMIT ? OFFSET ?";

        Object[] getByCategoryParams = new Object[]{size, offset};

        return this.jdbcTemplate.query(getPostersByCategoryQuery,
                (rs, rowNum) -> new GetUsedItemPosterRes(
                        rs.getInt("id"),
                        rs.getString("c.name"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("status"),
                        rs.getString("content"),
                        rs.getInt("hearts"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("modifiedAt")
                ), getByCategoryParams);
    }

}
