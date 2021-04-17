package com.blogs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("blogs")
public class Blog implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Date insert_time;
    private Date update_time;
    private String markdown;
    private String content;
    private String entry_type;
    private String entry_img;
    private List<Tag> tags;
    private String classify;
    private String status;
}
