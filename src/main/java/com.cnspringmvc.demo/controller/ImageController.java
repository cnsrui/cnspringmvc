package com.cnspringmvc.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImageController {

    private static final Log logger = LogFactory.getLog(ImageController.class);

    /**
     * 什么是 @RequestHeader String referer
     * 在浏览器中，当程序发出的所有http请求（不包括直接在地址栏中输入url），浏览器都会在我们的http请求的报文的头部，增加Referer这样一个header，它标示着请求的来源。
     * 例如： "Referer":"http://localhost:8080/cnweb/xxx
     */
    @RequestMapping(value = "/image_get/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable("id") String id,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestHeader String referer) {
        // 判定Http请求是来自程序而非手动填写的浏览器地址，作用是防止仅在浏览器输入地址就能下载文件
        if (referer != null) {
            String imageDirectory = request.getServletContext().
                    getRealPath("/WEB-INF/images");
            File file = new File(imageDirectory,id + ".jpg");
            if (file.exists()) {
                response.setContentType("image/jpg");
                byte[] buffer = new byte[1024];
                try (FileInputStream fis = new FileInputStream(file);
                     BufferedInputStream bis = new BufferedInputStream(fis);
                ) {
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (IOException ex) {
                    // do something,
                    // probably forward to an Error page
                }
            }
        }
    }
}
