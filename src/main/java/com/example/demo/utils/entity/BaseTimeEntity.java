package com.example.demo.utils.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
public class BaseTimeEntity {
    private Timestamp createdAt;
    private Timestamp modifiedAt;
}
