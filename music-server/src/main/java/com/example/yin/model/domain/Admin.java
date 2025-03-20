package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@TableName(value = "admin")//指定实体类和数据库表的映射关系。
@Data
public class Admin {
    //实现id的自增长
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
