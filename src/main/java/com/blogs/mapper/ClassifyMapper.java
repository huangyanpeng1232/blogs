package com.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassifyMapper extends BaseMapper<Classify> {

    List<Classify> getActiveClassify();

    List<Classify> getAllClassify();

    void updateToDelete(@Param("id") int id);
}
