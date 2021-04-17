package com.blogs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blogs.model.Blog;
import com.blogs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("blogs")
public class BlogsController {

    @Autowired
    private BlogService blogService;

    @Value("${page.size}")
    private int pageSize;

    @RequestMapping("getBlogs")
    public JSONObject getBlogs(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        int index = jsonObject.getIntValue("index");
        String status = jsonObject.getString("status");
        String searchText = jsonObject.getString("searchText");
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        result.put("pageSize", pageSize);

        List<Blog> blogs = blogService.getBlogs(index,searchText,status);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(blogs));
        result.put("blogs", array);
        return result;
    }

    @RequestMapping("getBlogById")
    public JSONObject getBlogById(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        int id = jsonObject.getIntValue("id");
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        Blog blog = blogService.getBlog(id);
        result.put("blog", blog);
        return result;
    }

    @RequestMapping("updateBlog")
    public JSONObject updateBlog(@RequestBody String body) {
        JSONObject request = JSONObject.parseObject(body);
        Blog blog = JSONObject.parseObject(body, Blog.class);

        JSONArray tags = request.getJSONArray("tags");
        blogService.addClassify(blog);
        blogService.updateBlog(blog);
        blogService.saveTags(blog,tags);
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

    @RequestMapping("showHideBlog")
    public JSONObject showBlog(@RequestBody String body) {
        JSONObject request = JSONObject.parseObject(body);
        int id = request.getIntValue("id");
        String status = request.getString("status");

        blogService.updateBlogStatus(id,status);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

    @RequestMapping("saveBlog")
    public JSONObject saveBlog(@RequestBody String body) {
        JSONObject request = JSONObject.parseObject(body);
        Blog blog = JSONObject.parseObject(body, Blog.class);
        if(blog.getId() == null){
            blog.setId(0);
        }
        blog.setStatus("1");
        JSONArray tags = request.getJSONArray("tags");
        blogService.addClassify(blog);
        blogService.insertBlog(blog);
        blogService.saveTags(blog,tags);
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

    @RequestMapping("delBlogById")
    public JSONObject delBlogById(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        int id = jsonObject.getIntValue("id");
        blogService.deleteBlog(id);
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

}
