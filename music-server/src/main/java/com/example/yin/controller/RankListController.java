package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.RankListRequest;
import com.example.yin.service.RankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//用户评价歌单
@RestController
public class RankListController {

    @Autowired
    private RankListService rankListService;


    /**
     * TODO 用户提交评分
     * 用户在前端提交评分
     */
    @PostMapping("/rankList/add")
    public R addRank(@RequestBody RankListRequest rankListAddRequest) {
        return rankListService.addRank(rankListAddRequest);
    }

    /**
     * TODO 用户端查看歌单评分
     * 获取歌单的总评分（包括所有用户对它的评分计算平均值）
     */
    @GetMapping("/rankList")
    public R rankOfSongListId(@RequestParam Long songListId) {
        return rankListService.rankOfSongListId(songListId);
    }

    /**
     * TODO 获取该用户对歌单的评分
     * 获取该用户对歌单评分
     */
    @GetMapping("/rankList/user")
    public R getUserRank(@RequestParam Long consumerId, @RequestParam Long songListId) {
        return rankListService.getUserRank(consumerId, songListId);
    }
}
