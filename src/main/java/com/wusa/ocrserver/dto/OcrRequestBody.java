package com.wusa.ocrserver.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class OcrRequestBody {
    private MultipartFile image;

    private String fileName;

    private String imageType;

    //    private String token;
}
