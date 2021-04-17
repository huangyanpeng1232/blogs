package com.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> getAllTag();

    @Select("select * form tag where id = #{id}")
    Tag getTagById(@Param("id") int id);

    void delTagById(@Param("id") int id);

    @Select("select t.* from blog_tag bt,tag t where bt.tagId = t.id and bt.blogId = #{id}")
    List<Tag> getTagByBlogId(@Param("id") int id);
}
