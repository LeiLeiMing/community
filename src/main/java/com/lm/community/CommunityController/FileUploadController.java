package com.lm.community.CommunityController;

import com.lm.community.Domain.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileUploadController {

    @PostMapping("/file/upload")
    @ResponseBody
    public FileUpload upload(){
        FileUpload upload = new FileUpload();
        upload.setSuccess(1);
        upload.setUrl("/img/life1.gif");
        return  upload;
    }

}
