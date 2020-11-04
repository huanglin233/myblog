package com.hl.myblog.common.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;

/**
 *fastdfs文件上传工具 
 * 
 * @author huanglin
 * @data   2020年8月20日-下午9:26:26
 */

@Component
public class FastDFSClient {
    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    
    @Autowired
    private FdfsWebServer fdfsWebServer;

    /**
     * 上传文件
     * 
     * @param  file 文件
     * @return 文件访问地址
     * @throws Exception
     */
    @RecordLog(detail = "上传多个文件:[{{file.getSize}}]个文件", recordType = RecordType.INSERT, recordObject = RecordObject.FASTDFS)
    public String uploadFile(MultipartFile file) throws Exception {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);

        return getResAccessUrl(storePath);
    }

    /**
     * 上传文件
     * @param  file 文件
     * @return 文件访问地址
     * @throws Exception
     */
    @RecordLog(detail = "上传文件[{{file.getName}}]", recordType = RecordType.INSERT, recordObject = RecordObject.FASTDFS)
    public String uploadFile(File file) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath       storePath   = fastFileStorageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);

        return getResAccessUrl(storePath);
    }

    /**
     * 下载文件
     * @param  fileUrl   文件路径
     * @return
     * @throws Exception
     */
    @RecordLog(detail = "下载文件[{{fileUrl}}]", recordType = RecordType.DOWNLOAD, recordObject = RecordObject.FASTDFS)
    public byte[] downloadFile(String fileUrl) throws Exception {
        String            group             = fileUrl.substring(0, fileUrl.indexOf("/"));
        String            path              = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[]            downloadFile      = fastFileStorageClient.downloadFile(group, path, downloadByteArray);

        return downloadFile;
    }

    /**
     * 删除文件
     * @param fileUrl 文件路径
     */
    @RecordLog(detail = "删除文件[{{fileUrl}}]", recordType = RecordType.DELETE, recordObject = RecordObject.FASTDFS)
    public void deleteFile(String fileUrl) {
        if(StringUtils.isEmpty(fileUrl)) {
            return;
        }

        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            logger.info("file delete fail msg = {}", e.toString());
        }
    }

    /**
     * 封装图片完整的url地址
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();

        logger.info("\n" + "upload file sccess, path = {}", fileUrl);

        return fileUrl;
    }
}