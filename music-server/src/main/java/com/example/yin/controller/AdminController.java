package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.AdminRequest;
import com.example.yin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 管理员/用户登录
 */

//在使用 @RestController 注解标记的类中，每个方法的返回值R类型都会以 JSON 或 XML 的形式直接写入 HTTP 响应体中，
// 相当于在每个方法上都添加了 @ResponseBody 注解
// Controller层在返回对象时，会自动将java对象转为json对象
@RestController
public class AdminController {
    //依赖注入
    @Autowired
    private AdminService adminService;

    //判断管理员是否登录成功
    //用@RequestBody来将jason数据传递到adminRequest当中
   //根据前端规定好的url，通过请求方式+Mapping（url）来获取前端的数具，对应前端的signIn登录功能

//    session对应一个http会话，用来记录用户的状态，记录http会话中所携带的数据
    @PostMapping("/admin/login/status")
    public R loginStatus(@RequestBody AdminRequest adminRequest, HttpSession session) {
        return adminService.verityPasswd(adminRequest, session);
    }
}
