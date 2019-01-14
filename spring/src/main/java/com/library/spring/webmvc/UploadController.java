package com.library.spring.webmvc;

import com.library.utils.itext.PdfUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuff on 2017/4/2.
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file) { //使用MultipartFile接受上传的文件
        try {
            //使用FileUtils.writeByteArrayToFile快速写文件到磁盘
            FileUtils.writeByteArrayToFile(new File("F:/upload/"+file.getOriginalFilename()), file.getBytes());
            return "ok";
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
    @RequestMapping(value = "/download/pdf")
    public @ResponseBody
    void download(final HttpServletResponse response) {
        String[] titles = new String[]{"标题1", "标题2", "", null, "标题5", "标题6", "标题7"};
        Object[] data1 = new Object[]{"数据1", true, 3.1415926124567812129D, new Date(System.currentTimeMillis()), null, 123};
        Object[] data2 = new Object[]{"数据2", false, 3.1415926D, new Date(System.currentTimeMillis()), "", "yy"};
        Object[] data3 = new Object[]{"数据3", true, 1234567890123456789L, new Timestamp(System.currentTimeMillis()), null, 456,"xx"};
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(null);
        data.add(new Object[]{});
        ByteArrayOutputStream out = PdfUtil.build(titles, data);

        String fileName = null;
        try {
            fileName = new String("测试.pdf".getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] responseData = out.toByteArray();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + responseData.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        try {
            response.getOutputStream().write(responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
