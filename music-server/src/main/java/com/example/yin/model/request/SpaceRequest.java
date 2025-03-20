package com.example.yin.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class SpaceRequest {
    private Integer id;

    private Integer userId;

    private String content;

    private Date createTime;

    private Integer up;//点赞

    private String pic;

}
