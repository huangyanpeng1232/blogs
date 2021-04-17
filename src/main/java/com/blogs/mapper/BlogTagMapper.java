package com.blogs.mapper;

import com.blogs.model.BlogTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogTagMapper {

    @Insert("INSERT INTO `blog_tag` (`blogId`, `tagId`) VALUES (#{blogId}, #{tagId})")
    void saveBlogTag(BlogTag blogTag);

    @Delete("delete from blog_tag where `blogId` = #{id}")
    void deleteByBlogId(@Param("id") int id);

    @Delete("delete from blog_tag where `tagId` = #{id}")
    void deleteByTagId(@Param("id") int id);
}
