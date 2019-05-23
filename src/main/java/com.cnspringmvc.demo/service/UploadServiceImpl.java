package com.cnspringmvc.demo.service;

import com.cnspringmvc.demo.domain.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description
 * @auther cnsrui
 * @create 2019-05-23 11:46
 */
public class UploadServiceImpl implements UploadService {

    @Override
    public void uploadFile(String uploadPath, UploadedFile uploadedFile) {

        // 如果保存上传文件的目录不存在，就创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            System.out.println("Upload Directory : " + uploadDir + " has been created successfully.");
        } else {
            System.out.println("Upload Directory : " + uploadDir + " had bean existed.");
        }

        MultipartFile multipartFile = uploadedFile.getMultipartFile();
        // 获取原始文件名称
        String fileName = multipartFile.getOriginalFilename();
        // 根据保存上传文件的路径与原始文件名称，创建该文件
        String filePath = uploadPath + File.separator + fileName;
        File storeFile = new File(filePath);

        // 根据路径和原始文件名称，创建文件
        try {
            // 保存已上传文件的核心方法，只需要 transferTo 即可
            multipartFile.transferTo(storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件 " + fileName + " 上传成功。");
        System.out.println("保存路径为： " + storeFile);
    }
}

