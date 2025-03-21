package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.SongRequest;
import com.example.yin.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;


//管理员对歌曲进行操作，用户搜索歌曲..
@RestController
public class SongController {

    @Autowired
    private SongService songService;


    //用于上传歌曲
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }


    /**
     * TODO 数据库中添加歌曲，前端页面展示
     */
    @PostMapping("/song/add")
    public R addSong(SongRequest addSongRequest, @RequestParam("file") MultipartFile mpfile) {
        return songService.addSong(addSongRequest,mpfile);
    }

    /**
     * TODO 数据库中删除歌曲，前端页面展示
     */
    @DeleteMapping("/song/delete")
    public R deleteSong(@RequestParam int id) {
        return songService.deleteSong(id);
    }

    /**
     * TODO 前端页面展示所有歌曲
     */
    @GetMapping("/song")
    public R allSong() {
        return songService.allSong();
    }


    // 返回指定歌曲ID的歌曲
    @GetMapping("/song/detail")
    public R songOfId(@RequestParam int id) {
        return songService.songOfId(id);
    }

    // 返回指定歌手ID的歌曲
    @GetMapping("/song/singer/detail")
    public R songOfSingerId(@RequestParam int singerId) {
        return songService.songOfSingerId(singerId);
    }

    // 返回指定歌曲名的歌曲
    @GetMapping("/song/singerName/detail")
    public R songOfSongName(@RequestParam String name) {
        return songService.songOfSongName(name);
    }

    // 更新歌曲信息
    @PostMapping("/song/update")
    public R updateSongMsg(@RequestBody SongRequest updateSongRequest) {
        return songService.updateSongMsg(updateSongRequest);
    }

    // 更新歌曲图片
    @PostMapping("/song/img/update")
    public R updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongPic(urlFile, id);
    }

    // 更新歌曲
    @PostMapping("/song/url/update")
    public R updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongUrl(urlFile, id);
    }
}
