package com.cnspringmvc.demo.service;

import com.cnspringmvc.demo.domain.Product;
import com.cnspringmvc.demo.domain.UploadedFile;

/**
 * @Description
 * @auther cnsrui
 * @create 2019-05-23 11:43
 */
public interface UploadService {
    void uploadFile(String uploadPath, UploadedFile uploadedFile);
    void uploadFiles(String uploadPath, Product product);
}
