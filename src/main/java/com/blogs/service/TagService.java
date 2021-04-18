package com.blogs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.BlogTagMapper;
import com.blogs.mapper.TagMapper;
import com.blogs.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    public List<Tag> getActiveTag(){
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("active","1");
        return tagMapper.selectList(tagQueryWrapper);
    }

    public Tag getTagByName(String name){
        Map<String,Object> query = new HashMap<>();
        query.put("name",name);
        List<Tag> tags = tagMapper.selectByMap(query);
        if(tags.size()>0){
            return tags.get(0);
        }else {
            return null;
        }
    }

    public List<Tag> getTagByBlogId(int id){
         return tagMapper.getTagByBlogId(id);
    }

    public void saveTag(Tag tag) {
        tagMapper.insert(tag);
    }

    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    public void updateTagById(Tag tag) {
        tagMapper.updateById(tag);
    }

    public void delTagById(int id) {
        blogTagMapper.deleteByTagId(id);
        tagMapper.delTagById(id);
    }
}
