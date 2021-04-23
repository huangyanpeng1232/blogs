package com.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.Color;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ColorMapper extends BaseMapper<Color> {

    @Select("select color from color where `status` = '1' ORDER BY rand() limit 1")
    Color randColor();
}
