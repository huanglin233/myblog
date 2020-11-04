package com.hl.myblog.web.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hl.myblog.common.utils.FastDFSClient;
import com.hl.myblog.vo.UploadVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * editormd文件上传
 * 
 * @author huanglin
 * @data   2020年9月2日-下午11:11:48
 */
@Controller
@CrossOrigin
@Api(value = "文件上传模块", tags = "文件上传接口")
public class FileUploadController {

    @Autowired
    private FastDFSClient fastDFSClient;

    @ApiOperation(value = "上传文件", notes = "上传文件到FastDFS文件服务上")
    @ApiImplicitParam(name = "file", value = "文件对象", paramType = "query", dataType = "MultipartFile")
    @ResponseBody
    @RequestMapping("/upload")
    public UploadVo uploadFile(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file) throws Exception {
        String   uploadFile = fastDFSClient.uploadFile(file);
        UploadVo uploadVo   = new UploadVo();
        if(StringUtils.isEmpty(uploadFile)) {
            uploadVo.success = 0;
            uploadVo.message = "上传失败";
        } else {
            uploadVo.success = 1;
            uploadVo.message = "上传成功";
        }
        uploadVo.url = uploadFile;

        return uploadVo;
    }
}