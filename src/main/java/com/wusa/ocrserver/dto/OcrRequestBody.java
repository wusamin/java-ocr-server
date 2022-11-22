package com.wusa.ocrserver.dto;

import org.springframework.web.multipart.MultipartFile;

public class OcrRequestBody {
    private MultipartFile image;

    private String fileName;

    private String imageType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }


    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    //    private String token;
}
