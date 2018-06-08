/**
 * @Project base-common
 * @Package com.cloud.base.service
 * @FileName FileUploadService.java
 */

package com.cloud.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.base.entity.BaseResult;

/**
 * 七牛文件服务
 * @Author zivin
 * @Date 2017年10月28日
 */
public interface FileUploadService {

    /**
     * 上传文件到七牛
     * @param files
     * @return
     * @throws Exception
     */
    List<String> upload(List<MultipartFile> files) throws Exception;

    /**
     * 删除七牛文件
     * @param paths
     * @return
     */
    BaseResult delete(String[] paths);

}
