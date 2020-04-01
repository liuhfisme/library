package com.library.scloud.learn.fastdfs.config;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 配置.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-04-01
 */
@Component
public class FastDFSClient {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    /**
     * 上传文件
     * @param file 文件对象
     * @return String 文件路径
     * @throws IOException String
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     * @param bytes 文件数据
     * @param format 文件格式（后缀）
     * @return String 文件路径
     */
    public String uploadFile(byte[] bytes, String format) {
        StorePath storePath = storageClient.uploadFile(new ByteArrayInputStream(bytes), bytes.length, format, null);
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @return String 文件路径
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        StorePath storePath = storageClient.uploadFile(FileUtils.openInputStream(file), file.length(), FilenameUtils.getExtension(file.getName()), null);
        return storePath.getFullPath();
    }

    /**
     * 把字符串作为指定格式的文件上传
     * @param content 字符串
     * @param fileExtension 文件格式
     * @return String 文件路径
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @return String 文件路径
     * @throws IOException
     */
    public String uploadImageAndCrtThumbImage(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * 根据图片路径获取缩略图路径（使用uploadImageAndCrtThumbImage方法上传图片）
     * @param filePath 图片路径
     * @return String 缩略图路径
     */
    public String getThumbImagePath(String filePath) {
        return thumbImageConfig.getThumbImagePath(filePath);
    }

    /**
     * 根据文件路径下载文件
     * @param filePath 文件路径
     * @return byte[] 文件字节数据
     * @throws IOException
     */
    public byte[] downFile(String filePath) throws IOException {
        StorePath storePath = StorePath.parseFromUrl(filePath);
        return storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadCallback<byte[]>() {
            @Override
            public byte[] recv(InputStream ins) throws IOException {
                return IOUtils.toByteArray(ins);
            }
        });
    }

    /**
     * 根据文件地址删除文件
     * @param filePath 文件访问地址
     * @return boolean 是否成功删除
     */
    public boolean deleteFile(String filePath) {
        try {
            StorePath storePath = StorePath.parseFromUrl(filePath);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            return true;
        } catch (FdfsUnsupportStorePathException e) {
            return false;
        }
    }

}
