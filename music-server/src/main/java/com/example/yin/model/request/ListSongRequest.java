package com.example.yin.model.request;

import lombok.Data;

/**
* 歌曲和歌单的归属关系
 **/
@Data
public class ListSongRequest {
    private Integer id;

    private Integer songId;

    private Integer songListId;
}
