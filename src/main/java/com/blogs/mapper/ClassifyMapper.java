package com.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.Classify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassifyMapper extends BaseMapper<Classify> {

    List<Classify> getActiveClassify();

    List<Classify> getAllClassify();
}
