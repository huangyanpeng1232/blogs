package com.blogs.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("login")
    public JSONObject login(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        String password = jsonObject.getString("password");

        JSONObject result = new JSONObject();

        if("246811".equals(password)){
            result.put("status", "succeed");
        }else{
            result.put("status", "error");
        }
        return result;
    }
}
