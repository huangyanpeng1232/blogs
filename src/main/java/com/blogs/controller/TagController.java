package com.blogs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blogs.model.Blog;
import com.blogs.model.Classify;
import com.blogs.model.Tag;
import com.blogs.service.BlogService;
import com.blogs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Value("${page.size}")
    private int pageSize;

    @Autowired
    private BlogService blogService;

    @RequestMapping("getBlogsByTagId")
    public JSONObject getBlogsByTagId(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        int index = jsonObject.getIntValue("index");
        String tagId = jsonObject.getString("tagId");
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        result.put("pageSize", pageSize);

        List<Blog> blogs = blogService.getBlogsByTagId(index,tagId);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(blogs));
        result.put("blogs", array);
        return result;
    }

    @RequestMapping("getActiveTag")
    public JSONObject getActiveTagList(){
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        List<Tag> tagList = tagService.getActiveTag();
        result.put("tagList", tagList);
        return result;
    }

    @RequestMapping("getAllTag")
    public JSONObject getAllTag() {

        List<Tag> tagList = tagService.getAllTag();
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(tagList));
        result.put("tagList", array);
        return result;
    }

    @RequestMapping("updateTag")
    public JSONObject updateTag(@RequestBody String body) {

        Tag tag = JSONObject.parseObject(body, Tag.class);
        if(tag == null){
            JSONObject result = new JSONObject();
            result.put("status", "error");
            return result;
        }

        tagService.updateTagById(tag);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

    @RequestMapping("delTagById")
    public JSONObject delTagById(@RequestBody String body) {
        JSONObject request = JSONObject.parseObject(body);
        int id = request.getIntValue("id");
        tagService.delTagById(id);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }
}
