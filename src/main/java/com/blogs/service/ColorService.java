package com.blogs.service;

import com.blogs.mapper.ColorMapper;
import com.blogs.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {

    @Autowired
    private ColorMapper colorMapper;

    public Color randColor(){
        return colorMapper.randColor();
    }
}
