package com.blogs.controller;

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
@RequestMapping("search")
public class SearchController {

    @Value("${page.search-size}")
    private int pageSearchSize;

    @Autowired
    private BlogService blogService;

    @RequestMapping("searchBlog")
    public JSONObject searchBlog(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        String searchText = jsonObject.getString("searchText");
        List<Blog> blogs = blogService.getBlogs(1, searchText,"1", pageSearchSize);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        result.put("blogs", blogs);
        return result;
    }
}
