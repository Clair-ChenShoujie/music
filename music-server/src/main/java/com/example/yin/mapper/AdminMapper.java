package com.example.yin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yin.model.domain.Admin;
import org.springframework.stereotype.Repository;

//MyBatis-Plus 的核心类 BaseMapper 主要是用于提供基本的 CRUD（创建、读取、更新、删除）操作的接口定义
//Mapper层对数据库进行数据持久化操作，他的方法语句是直接针对数据库操作
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

}
