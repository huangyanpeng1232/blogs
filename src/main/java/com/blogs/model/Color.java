package com.blogs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Color {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String name;
    private String color;
    private String insert_time;
    private String status;
}
