package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.UploadedFile;
import com.cnspringmvc.demo.service.UploadService;
import com.cnspringmvc.demo.service.UploadServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class Html5FileUploadController {
    private static final String UPLOAD_DIRECTORY = "files";


    private static final Log logger = LogFactory
            .getLog(Html5FileUploadController.class);

    @RequestMapping(value = "/html5")
    public String inputProduct() {
        return "Html5";
    }

    @RequestMapping(value = "/file_upload")
    public void saveFile(HttpServletRequest servletRequest,
            @ModelAttribute UploadedFile uploadedFile,
            BindingResult bindingResult, Model model) {


        String uploadPath = servletRequest.getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;

        UploadService uploadService = new UploadServiceImpl();
        uploadService.uploadFile(uploadPath, uploadedFile);

/*

        // 如果保存上传文件的目录不存在，就创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            System.out.println("Upload Directory : "+ uploadDir + " has been created successfully.");
        }
        else{
            System.out.println("Upload Directory : "+ uploadDir + " had bean existed.");
        }

        MultipartFile multipartFile = uploadedFile.getMultipartFile();
        // 获取原始文件名称
        String fileName = multipartFile.getOriginalFilename();
        // 根据保存上传文件的路径与原始文件名称，创建该文件
        String filePath = uploadPath  + File.separator+ fileName;
        File storeFile = new File(filePath);

        // 根据路径和原始文件名称，创建文件
        try {
            // 保存已上传文件的核心方法，只需要 transferTo 即可
            multipartFile.transferTo(storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件 " + fileName + " 上传成功。");
        System.out.println("保存路径为： " + storeFile);*/
    }
}
