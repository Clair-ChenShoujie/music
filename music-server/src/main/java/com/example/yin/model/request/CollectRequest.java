package com.example.yin.model.request;

import lombok.Data;

import java.util.Date;

/**
 * 收藏歌曲相关操作的请求类型
 **/
@Data
public class CollectRequest {
    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer songId;

    private Integer songListId;

    private Date createTime;
}
