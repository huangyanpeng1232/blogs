package com.blogs.controller;

import com.alibaba.fastjson.JSONObject;
import com.blogs.model.BlogFile;
import com.blogs.service.FileService;
import com.blogs.utils.StringUtil;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.secretKey}")
    private String minioSecretKey;

    @RequestMapping("uploadFile")
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        if(file == null){
            return null;
        }

        String user = "admin/";

        MinioClient minioClient = new MinioClient(minioUrl, minioAccessKey, minioSecretKey);
        InputStream is= file.getInputStream(); //得到文件流
        String[] split = file.getOriginalFilename().split("\\.");
        String fileName = user+ StringUtil.uuid()+"."+split[split.length-1]; //文件名

        minioClient.putObject("blog",fileName,is,file.getContentType());
        String url = minioClient.getObjectUrl("blog", fileName);
        BlogFile blogFile = new BlogFile();
        blogFile.setUpload_time(new Date());
        blogFile.setObject_name(fileName);
        fileService.saveFile(blogFile);

        JSONObject result = new JSONObject();
        result.put("status", "succeed");
        result.put("url", url);
        return result;
    }
}
