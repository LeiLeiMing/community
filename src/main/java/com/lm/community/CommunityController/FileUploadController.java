package com.lm.community.CommunityController;

import com.lm.community.Domain.FileUpload;
import com.lm.community.Utils.OSSFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@Controller
public class FileUploadController {

    @Autowired
    private OSSFileUpload ossFileUpload;

    /**
     * 图片上传OSS
     * @param request
     * @return
     */
    @PostMapping("/file/upload")
    @ResponseBody
    public FileUpload upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename();
            URL url = ossFileUpload.uploadFile(bytes, filename);
            String s = url.toString();
            FileUpload upload = new FileUpload();
            upload.setSuccess(1);
            upload.setUrl(s);
            return  upload;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
