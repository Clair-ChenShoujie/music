package com.example.yin.model.request;

import lombok.Data;

/**
* 用户对歌单的评价
 **/
@Data
public class RankListRequest {
    private Long id;

    private Long songListId;

    private Long consumerId;

    private Integer score;
}
