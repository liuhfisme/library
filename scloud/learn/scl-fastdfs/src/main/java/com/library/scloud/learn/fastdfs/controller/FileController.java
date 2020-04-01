package com.library.scloud.learn.fastdfs.controller;

import com.library.scloud.learn.fastdfs.config.FastDFSClient;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件上传下载控制器.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-04-01
 */
@Controller
public class FileController {
    @Autowired
    private FastDFSClient fastDFSClient;

    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) { //使用MultipartFile接受上传的文件
        try {
            return fastDFSClient.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }

    /**
     * 下载测试
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @GetMapping(value = "/download")
    public void download(final HttpServletResponse response, String filePath) {
        try {
            byte[] bytes = fastDFSClient.downFile(filePath);
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
