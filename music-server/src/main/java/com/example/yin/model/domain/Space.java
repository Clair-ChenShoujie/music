package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "space")//指定实体类和数据库表的映射关系。
@Data
public class Space {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Integer up;

    private String pic;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
