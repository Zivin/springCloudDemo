/**
 * @Project base-common
 * @Package com.cloud.base.service.impl
 * @FileName FileUploadServiceImpl.java
 */

package com.cloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.entity.BaseResult;
import com.cloud.service.FileUploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 七牛文件服务实现
 * @Author zivin
 * @Date 2017年10月28日
 */
@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    /**
     * accessKey
     */
    @Value("${qiniu.accessKey}")
    private String accessKey;

    /**
     * secretKey
     */
    @Value("${qiniu.secretKey}")
    private String secretKey;

    /**
     * bucket 空间名称
     */
    @Value("${qiniu.bucket}")
    private String bucket;

    /**
     * domain 空间对应域名
     */
    @Value("${qiniu.domain}")
    private String domain;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.FileUploadService#upload(java.util.List)
     */
    @Override
    public List<String> upload(List<MultipartFile> files) throws Exception {
        List<String> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(files)) {
            // 构建七牛授权
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Configuration config = new Configuration(Zone.zone2());
            UploadManager uploadManager = new UploadManager(config);

            for (MultipartFile multipartFile : files) {
                try {
                    Response response = uploadManager.put(multipartFile.getBytes(), null, upToken);
                    JSONObject callback = JSON.parseObject(response.bodyString());
                    result.add(domain + callback.getString("key"));
                } catch (Exception e) {
                    logger.error("七牛文件上传失败:", e);
                    throw new Exception("七牛文件上传失败");
                }
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.service.FileUploadService#delete(java.util.List)
     */
    @Override
    public BaseResult delete(String[] paths) {
        if (paths != null && paths.length > 0) {
            // 构建七牛授权
            Auth auth = Auth.create(accessKey, secretKey);

            Configuration config = new Configuration(Zone.zone2());
            BucketManager bucketManager = new BucketManager(auth, config);

            for (String path : paths) {
                try {
                    String[] temp = path.split("/");
                    if (temp != null && temp.length > 0) {
                        String key = temp[temp.length - 1];
                        bucketManager.delete(bucket, key);
                    }
                } catch (QiniuException e) {
                    logger.error("七牛文件删除失败:", e);
                }
            }

        }
        return BaseResult.success();
    }

}
