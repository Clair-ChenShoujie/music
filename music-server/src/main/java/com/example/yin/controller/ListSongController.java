package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.ListSongRequest;
import com.example.yin.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//管理员端对歌单里的歌曲进行增删改查操作
@RestController
public class ListSongController {

    @Autowired
    private ListSongService listSongService;


    /**
     * TODO 管理员端调用
     *  向歌单中增加歌曲
     */
    @PostMapping("/listSong/add")
    public R addListSong(@RequestBody ListSongRequest addListSongRequest) {
        return listSongService.addListSong(addListSongRequest);
    }

    /**
     * TODO 管理员端调用
     *  删除歌单中歌曲
     */
    @GetMapping("/listSong/delete")
    public R deleteListSong(@RequestParam int songId) {
        return listSongService.deleteListSong(songId);
    }

    /**
     * TODO 用户端调用
     *  查询歌单的所有歌曲
     */
    @GetMapping("/listSong/detail")
    public R listSongOfSongId(@RequestParam int songListId) {
        return listSongService.listSongOfSongId(songListId);
    }

    /**
     * TODO 管理员端调用
     *  向对歌单里面的歌曲信息进行更新（是否被删除，是否有新增..）
     */
    @PostMapping("/listSong/update")
    public R updateListSongMsg(@RequestBody ListSongRequest updateListSongRequest) {
        return listSongService.updateListSongMsg(updateListSongRequest);
    }
}
