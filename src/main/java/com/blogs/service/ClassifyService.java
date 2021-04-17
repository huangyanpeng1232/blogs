package com.blogs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.ClassifyMapper;
import com.blogs.model.Classify;
import com.blogs.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Cacheable(value = "getClassifyByName", key = "#name")
    public Classify getClassifyByName(String name){
        Map<String,Object> query = new HashMap<>();
        query.put("name",name);
        List<Classify> classifies = classifyMapper.selectByMap(query);
        if(classifies.size()>0){
            return classifies.get(0);
        }else {
            return null;
        }
    }

    @CachePut(cacheNames = "getClassifyByName",key = "#classify.name")
    public void saveClassify(Classify classify) {
        classifyMapper.insert(classify);
    }

    @Cacheable(value = "getActiveClassify")
    public List<Classify> getActiveClassify() {
        return classifyMapper.getActiveClassify();
    }
    
    public List<Classify> getAllClassify() {
        return classifyMapper.getAllClassify();
    }

    public void delClassifyById(int id) {
        classifyMapper.deleteById(id);
    }

    public void updateClassifyById(Classify classify) {
        classifyMapper.updateById(classify);
    }

}
