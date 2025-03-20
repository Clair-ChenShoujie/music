package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.CommentRequest;
import com.example.yin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//歌曲评论
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;


    // 提交评论
    @PostMapping("/comment/add")
    public R addComment(@RequestBody CommentRequest addCommentRequest) {
        return commentService.addComment(addCommentRequest);
    }

    // 删除评论,前端获取该条评论的id，通过id在数据库中直接删除
    @GetMapping("/comment/delete")
    public R deleteComment(@RequestParam Integer id) {
        return commentService.deleteComment(id);
    }

    // 获得指定歌曲的评论列表
    //前端所传的url形式为`comment/song/detail?songId=${id}`，而后端为'/comment/song/detail'
    //目的是将前端所传的参数（在detail后面的部分）使用自己创建的类通过注解@RequestParam来进行接收前端的参数
    @GetMapping("/comment/song/detail")
    public R commentOfSongId(@RequestParam Integer songId) {
        return commentService.commentOfSongId(songId);
    }

    // 获得指定歌单的评论列表
    @GetMapping("/comment/songList/detail")
    public R commentOfSongListId(@RequestParam Integer songListId) {
        return commentService.commentOfSongListId(songListId);
    }

    //获取指定动态中的评论列表
    @GetMapping("/comment/space/detail")
    public R commentOfSpaceId(@RequestParam Integer spaceId){
        return commentService.commentOfSpaceId(spaceId);
    }

    //更新评论的点赞数
    @PostMapping("/comment/like")
    public R commentOfLike(@RequestBody CommentRequest upCommentRequest) {
        return commentService.updateCommentMsg(upCommentRequest);
    }
}
