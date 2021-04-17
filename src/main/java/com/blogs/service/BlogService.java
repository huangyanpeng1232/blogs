package com.blogs.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blogs.mapper.BlogMapper;
import com.blogs.mapper.BlogTagMapper;
import com.blogs.mapper.TagMapper;
import com.blogs.model.Blog;
import com.blogs.model.BlogTag;
import com.blogs.model.Classify;
import com.blogs.model.Tag;
import com.blogs.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.api.client.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private BlogMapper blogMapper;

    @Value("${page.size}")
    private int pageSize;

    @Cacheable(value = "getBlogs", key = "#index+'_'+#status")
    public List<Blog> getBlogs(int index,String searchText,String status){
        PageHelper.startPage(index,pageSize);
        List<Blog> blogs = blogMapper.getBlogList(searchText,status);
        addTags(blogs);
        return blogs;
    }

    public List<Blog> getBlogs(int index,String searchText,String status,int pageSize){
        PageHelper.startPage(index,pageSize);
        List<Blog> blogs = blogMapper.getBlogList(searchText,status);
        addTags(blogs);
        return blogs;
    }

    @Cacheable(value = "getBlog", key = "#id")
    public Blog getBlog(int id) {
        Blog blog = blogMapper.getBlogById(id);
        addTags(blog);
        return blog;
    }

    @Cacheable(value = "getBlogsByClassId", key = "#index+'_'+#classifyId")
    public List<Blog> getBlogsByClassId(int index,String classifyId){
        PageHelper.startPage(index,pageSize);
        List<Blog> blogs = blogMapper.getBlogListByClassId(classifyId);
        addTags(blogs);
        return blogs;
    }

    @Cacheable(value = "getBlogsByTagId", key = "#index+'_'+#classifyId")
    public List<Blog> getBlogsByTagId(int index,String tagId){
        PageHelper.startPage(index,pageSize);
        List<Blog> blogs = blogMapper.getBlogListByTagId(tagId);
        addTags(blogs);
        return blogs;
    }

    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }

    public void insertBlog(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    public void saveTags(Blog blog, JSONArray tagIds){
        blogTagMapper.deleteByBlogId(blog.getId());
        if(tagIds != null){
            for(int i = 0;i<tagIds.size();i++){
                Object o = tagIds.get(i);
                JSONObject tag = JSONObject.parseObject(JSONObject.toJSONString(o));
                Integer id = tag.getInteger("id");
                if(id != null){
                    BlogTag bt = new BlogTag();
                    bt.setBlogId(blog.getId());
                    bt.setTagId(id);
                    blogTagMapper.saveBlogTag(bt);
                }else{
                    Tag tag1 = new Tag();
                    tag1.setColor("rgb(71, 212, 255)");
                    tag1.setActive(0);
                    tag1.setName(tag.getString("name"));
                    tag1.setInsert_time(new Date());
                    tagMapper.insert(tag1);

                    BlogTag bt = new BlogTag();
                    bt.setBlogId(blog.getId());
                    bt.setTagId(tag1.getId());
                    blogTagMapper.saveBlogTag(bt);

                }
            }
        }
    }

    public void addClassify(Blog blog){
        Classify classify = classifyService.getClassifyByName(blog.getClassify());
        if(classify == null){
            classify = new Classify();
            classify.setInsert_time(new Date());
            classify.setName(blog.getClassify());
            classify.setActive(0);
            classify.setColor("rgb(71, 212, 255)");
            classifyService.saveClassify(classify);
        }
        blog.setClassify(String.valueOf(classify.getId()));
    }

    public void deleteBlog(int id) {
        blogMapper.deleteById(id);
    }

    private void addTags(Blog blog) {
        blog.setTags(tagService.getTagByBlogId(blog.getId()));
    }

    public void addTags(List<Blog> blogs){
        for (Blog blog : blogs) {
            addTags(blog);
        }
    }

    public void updateBlogStatus(int id, String status) {
        blogMapper.updateBlogStatus(id,status);
    }
}
