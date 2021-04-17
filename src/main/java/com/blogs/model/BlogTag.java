package com.blogs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlogTag implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer blogId;
    private Integer tagId;
}
