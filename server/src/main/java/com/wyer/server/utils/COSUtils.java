package com.wyer.server.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Name: COSUtils
 * Writer: wyer
 **/
@Component
public class COSUtils {

    @Autowired
    private COSClient cosClient;

    public String uploadObject(File localFile, String filePath) {
        // 指定文件将要存放的存储桶
        String bucketName = "wbwy-1318322348";
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return putObjectRequest.getKey();
    }

}
