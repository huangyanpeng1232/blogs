package com.blogs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("blog_file")
public class BlogFile implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String object_name;
    private Date upload_time;
}
