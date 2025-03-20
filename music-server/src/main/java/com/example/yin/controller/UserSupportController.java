package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.UserSupportRequest;
import com.example.yin.service.UserSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*  用户点赞
 **/
@RestController
@RequestMapping("/userSupport")
public class UserSupportController {

    @Autowired
    UserSupportService userSupportService;

    //查询用户是否对该条评论进行了点赞

    @PostMapping("/test")
    public R isUserSupportComment(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.isUserSupportComment(userSupportRequest);
    }

    //用户进行点赞
    @PostMapping("/insert")
    public R insertCommentSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.insertCommentSupport(userSupportRequest);
    }

    //用户取消点赞
    @PostMapping("/delete")
    public R deleteCommentSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.deleteCommentSupport(userSupportRequest);
    }

    @PostMapping("/space/test")
    public R isUserSupportSpace(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.isUserSupportSpace(userSupportRequest);
    }

    //用户进行点赞
    @PostMapping("/space/insert")
    public R insertSpaceSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.insertSpaceSupport(userSupportRequest);
    }

    //用户取消点赞
    @PostMapping("/space/delete")
    public R deleteSpaceSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.deleteSpaceSupport(userSupportRequest);
    }

}
