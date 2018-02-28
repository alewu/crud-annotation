package com.ale.common;


import com.ale.common.response.Response;
import com.ale.common.util.OssClientUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

/**
 * @author alewu
 * @date 2018/1/9
 * @description 文件上传至阿里云
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @PostMapping("/files")
    public Response uploadFile(@RequestParam("files") MultipartFile[] files) {
        List<String> imageUrls = new LinkedList<>();
        for (MultipartFile file : files) {
            String imageUrl = OssClientUtil.uploadObject(file);
            imageUrls.add(imageUrl);
            System.out.println(file.getOriginalFilename() + imageUrl);
        }
        return Response.ok().put("imageUrls", imageUrls);
    }


}
