package com.example.demo.src.purchase;

import com.example.demo.src.purchase.model.PostPurchaseReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PurchaseDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int purchase(PostPurchaseReq postPurchaseReq) {
        String purchaseQuery = "INSERT INTO Purchase (usedItemPosterId,userId) " +
                "VALUES (?,?)";
        Object[] purchaseParams = new Object[]{postPurchaseReq.getUsedItemPosterId(),postPurchaseReq.getBuyerId()};


        this.jdbcTemplate.update(purchaseQuery,purchaseParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

}
