package com.lm.community.Utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

@Service
public class OSSFileUpload {
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    private String accessKeySecret = "94YOOmAsOBSdb33WUvxeLu64F4ndAv";
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.endpoint}")
    private String endpoint;


    public URL uploadFile(byte[] bytes, String name){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String objectName = name;
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        // 指定过期时间为n年。
        Date expiration = new Date(new Date().getTime() + Integer.MAX_VALUE );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        //获取云服务器上文件的地址
        URL url = ossClient.generatePresignedUrl(req);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }
}
