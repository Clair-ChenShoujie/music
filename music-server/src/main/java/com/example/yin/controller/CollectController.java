package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.CollectRequest;
import com.example.yin.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    // 添加收藏的歌曲
//    传送一个参数数组（包含用户、歌单还是歌曲、以及id）
    @PostMapping("/collection/add")
    public R addCollection(@RequestBody CollectRequest addCollectRequest) {
        return collectService.addCollection(addCollectRequest);
    }

    // 取消收藏的歌曲,前端中的url会携带用户id和歌曲id（分开传送，作为两个参数）
    @DeleteMapping("/collection/delete")
    public R deleteCollection(@RequestParam Integer userId, @RequestParam Integer songId) {
        return collectService.deleteCollect(userId, songId);
    }

    // 判断是否已经收藏歌曲
    // 如果已经收藏了该歌曲，前端会将音乐播放器旁的爱心变为红色
    @PostMapping("/collection/status")
    public R isCollection(@RequestBody CollectRequest isCollectRequest) {
        return collectService.existSongId(isCollectRequest);

    }

    // 在个人主页展示用户收藏的歌曲
    @GetMapping("/collection/detail")
    public R collectionOfUser(@RequestParam Integer userId) {
        return collectService.collectionOfUser(userId);
    }

    //管理员端调用展示歌曲收藏榜
    @GetMapping("/collection/sort")
    public R collectionOfSort(){return collectService.collectionOfSort();}
}
