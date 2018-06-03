package com.how2java.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: zhaowanyue
 * @date: 2018/6/3
 * @description:其中有一个MultipartFile 类型的属性，用于接受上传文件的注入
 */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
