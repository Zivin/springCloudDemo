/**
 * @Project base-common
 * @Package com.cloud.base.controller
 * @FileName FileUploadController.java
 */

package com.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cloud.base.controller.BaseController;
import com.cloud.base.entity.BaseResult;
import com.cloud.service.FileUploadService;

/**
 * 文件上传controller
 * @Author zivin
 * @Date 2017年10月28日
 */
@RestController
@RequestMapping(value = "/fileUpload")
public class FileUploadController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 上传文件
     * @param multipartRequest
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult upload(MultipartHttpServletRequest multipartRequest) {
        try {
            List<String> paths = fileUploadService.upload(multipartRequest.getFiles("file"));
            // 删除旧文件
            fileUploadService.delete(multipartRequest.getParameterValues("oldFilePath"));
            return BaseResult.success(paths);
        } catch (Exception e) {
            logger.error("七牛文件上传失败:", e);
            return BaseResult.failure();
        }
    }

    /**
     * 删除文件
     * @param paths
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(String[] paths) {
        try {
            return fileUploadService.delete(paths);
        } catch (Exception e) {
            logger.error("七牛文件删除失败:", e);
            return BaseResult.failure();
        }
    }

}
