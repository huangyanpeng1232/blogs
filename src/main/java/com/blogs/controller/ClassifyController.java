package com.blogs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blogs.model.Blog;
import com.blogs.model.Classify;
import com.blogs.service.BlogService;
import com.blogs.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("classify")
public class ClassifyController {

    @Value("${page.size}")
    private int pageSize;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ClassifyService classifyService;

    @RequestMapping("getActiveClassify")
    public JSONObject getActiveClassify() {

        List<Classify> classifyList = classifyService.getActiveClassify();
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(classifyList));
        result.put("classifyList", array);
        return result;
    }

    @RequestMapping("getAllClassify")
    public JSONObject getAllClassify() {

        List<Classify> classifyList = classifyService.getAllClassify();
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(classifyList));
        result.put("classifyList", array);
        return result;
    }

    @RequestMapping("updateClassify")
    public JSONObject updateClassify(@RequestBody String body) {

        Classify classify = JSONObject.parseObject(body, Classify.class);
        if(classify == null){
            JSONObject result = new JSONObject();
            result.put("status", "error");
            return result;
        }

        classifyService.updateClassifyById(classify);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

    @RequestMapping("delClassifyById")
    public JSONObject delClassifyById(@RequestBody String body) {
        JSONObject request = JSONObject.parseObject(body);
        int id = request.getIntValue("id");
        classifyService.delClassifyById(id);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }

    @RequestMapping("updateClassifyById")
    public JSONObject updateClassifyById(@RequestBody String body) {
        JSONObject request = JSONObject.parseObject(body);
        int id = request.getIntValue("id");
        String name = request.getString("name");
        String color = request.getString("color");
        int active = request.getIntValue("active");
        Classify classify = new Classify();
        classify.setId(id);
        classify.setName(name);
        classify.setColor(color);
        classify.setActive(active);
        classifyService.updateClassifyById(classify);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        return result;
    }


    @RequestMapping("getBlogsByClassId")
    public JSONObject getBlogsByClassId(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        int index = jsonObject.getIntValue("index");
        String classifyId = jsonObject.getString("classifyId");
        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        result.put("pageSize", pageSize);

        List<Blog> blogs = blogService.getBlogsByClassId(index,classifyId);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(blogs));
        result.put("blogs", array);
        return result;
    }
}
