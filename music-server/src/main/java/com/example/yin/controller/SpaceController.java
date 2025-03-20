package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.SpaceRequest;
import com.example.yin.service.CommentService;
import com.example.yin.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    //添加动态
    @PostMapping("/space/add")
    public R addSpace(@RequestBody SpaceRequest addSpaceRequest){
        return spaceService.addSpace(addSpaceRequest);
    }

    //删除动态
    @GetMapping("/space/delete")
    public R deleteSpace(@RequestParam Integer id) {
        return spaceService.deleteSpace(id);
    }

    //获取动态点赞，动态中的评论点赞可以从commentController中获取
    @PostMapping("/space/like")
    public R spaceOfLike(@RequestBody SpaceRequest upSpaceRequest){
        return spaceService.updateSpaceMsg(upSpaceRequest);
    }

    //展示所有动态
    @GetMapping("/space")
    public R allSpace(){
        return spaceService.allSpace();
    }

    // 更新歌手头像
    @PostMapping("/space/upload")
    public R updateSpacePic(@RequestParam("file") MultipartFile File) {
        return spaceService.updateSpacePic(File);
    }

}
