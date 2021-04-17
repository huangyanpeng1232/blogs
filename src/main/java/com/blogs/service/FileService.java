package com.blogs.service;

import com.blogs.mapper.FileMapper;
import com.blogs.model.BlogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    public void saveFile(BlogFile blogFile){
        fileMapper.insert(blogFile);
    }
}
