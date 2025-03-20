package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.ConsumerRequest;
import com.example.yin.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    /**
     * TODO 用户端页面调用 注册
     * 用户注册
     */
    @PostMapping("/user/add")
    public R addUser(@RequestBody ConsumerRequest registryRequest) {
        return consumerService.addUser(registryRequest);
    }

    /**
     * TODO 用户端页面调用  登录
     * 登录判断
     */
    @PostMapping("/user/login/status")
    public R loginStatus(@RequestBody ConsumerRequest loginRequest, HttpSession session) {
        log.info("用户正在登录");
        return consumerService.loginStatus(loginRequest, session);
    }

    /**
     * TODO 管理界面调用
     * 返回所有用户
     */
    @GetMapping("/user")
    public R allUser() {
        return consumerService.allUser();
    }


    /**
     * TODO 用户界面调用
     * 个人主页获取用户信息
     */
    @GetMapping("/user/detail")
    public R userOfId(@RequestParam int id) {
        return consumerService.userOfId(id);
    }

    /**
     * TODO 管理界面的调用
     * 删除用户
     */
    @GetMapping("/user/delete")
    public R deleteUser(@RequestParam int id) {
        return consumerService.deleteUser(id);
    }

    /**
     * TODO 用户端调用
     * 用户修改个人信息
     */
    @PostMapping("/user/update")
    public R updateUserMsg(@RequestBody ConsumerRequest updateRequest) {
        return consumerService.updateUserMsg(updateRequest);
    }

    /**
     * TODO 用户端调用
     * 用户修改个人密码，前端传入的会有新密码和旧密码
     * 用户要先根据旧密码验证该密码是否正确，正确了才能修改密码
     */
    @PostMapping("/user/updatePassword")
    public R updatePassword(@RequestBody ConsumerRequest updatePasswordRequest) {
        return consumerService.updatePassword(updatePasswordRequest);
    }

    /**
     * TODO 用户端调用
     * 用户更新用户头像
     */
    @PostMapping("/user/avatar/update")
    public R updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return consumerService.updateUserAvator(avatorFile, id);
    }

    @GetMapping("/user/region")
    public R regionOfUser(){
        return consumerService.regionOfUser();
    }
}
