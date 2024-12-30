package com.zzy.piccenter.demos.web.infrastructure.manager;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import com.qcloud.cos.model.ciModel.persistence.PicOperations;
import com.qcloud.cos.utils.IOUtils;
import com.zzy.piccenter.demos.web.config.CosClientConfig;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author T041018
 */
@Component
@Slf4j
public class CosManager {  
  
    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;


    /**
     * 测试上传对象
     * @param key
     * @param file
     */
    public PutObjectResult testPutObjectResult(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        return cosClient.putObject(putObjectRequest);
    }

    public COSObject testGetObject(String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        return cosClient.getObject(getObjectRequest);
    }

    /**
     * 上传对象返回详细信息
     * @param key
     * @param file
     * @return
     */
    public PutObjectResult putObjectResult(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        PicOperations picOperations = new PicOperations();
        picOperations.setIsPicInfo(1);
        putObjectRequest.setPicOperations(picOperations);
        return cosClient.putObject(putObjectRequest);
    }

    public String getHost() {
        return cosClientConfig.getHost();
    }

    @SneakyThrows
    public void downloadFile(String filepath, HttpServletResponse response) {
        COSObjectInputStream cosObjectInput = null;
        try {
            COSObject cosObject = testGetObject(filepath);
            cosObjectInput = cosObject.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);

            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filepath);

            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("file download error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "下载失败");
        } finally {
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }
    }
  
}
