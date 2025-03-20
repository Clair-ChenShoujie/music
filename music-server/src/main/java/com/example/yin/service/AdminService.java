package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.R;
import com.example.yin.model.domain.Admin;
import com.example.yin.model.request.AdminRequest;

import javax.servlet.http.HttpSession;

//IService是Mybatis=plus中的接口，该接口规定了要有对应的实现类ServiceImpl，封装了常见的业务层逻辑
public interface AdminService extends IService<Admin> {

    R verityPasswd(AdminRequest adminRequest, HttpSession session);
}
