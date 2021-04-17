package com.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    List<Blog> getBlogList(@Param("searchText") String searchText,@Param("status") String status);

    Blog getBlogById(@Param("id") int id);

    void updateBlog(Blog blog);

    void updateBlogStatus(@Param("id") int id,@Param("status") String status);

    void insertBlog(Blog blog);

    List<Blog> getBlogListByClassId(@Param("classifyId") String classifyId);

    List<Blog> getBlogListByTagId(@Param("tagId") String classifyId);
}
