package com.wusa.ocrserver.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wusa.ocrserver.base.RelationalDto;
import com.wusa.ocrserver.dto.OcrRequestBody;
import com.wusa.ocrserver.image.KancollePcImage;
import com.wusa.ocrserver.service.OcrService;

@RestController
@RequestMapping(value = "/ocr")
public class OcrController {

    @Autowired
    OcrService ocrService;

    static {
        //        System.loadLibrary("libtesseract3051.dll");
        System.load("/app/lib/tesseract/linux-x86-64/libtesseract.so");
        System.load("/app/lib/tesseract/others/libarchive.so.13");
    }

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> test() {
        return Map.of("message", "ok");
    }

    @RequestMapping(value = "/ocrtest", produces = MediaType.APPLICATION_JSON_VALUE)
    public RelationalDto ocrTest() {

        return ocrService.ocrTest();
    }

    @PostMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object doOcr(@RequestBody OcrRequestBody reqBody) {
        try {
            return ocrService.doOcr(
                    ImageIO.read(new ByteArrayInputStream(
                            reqBody.getImage().getBytes())),
                    KancollePcImage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", e);
        }
    }

    @PostMapping(value = "/imagetest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object doOcr2(@ModelAttribute OcrRequestBody reqBody) {
        System.out.println(reqBody.getImage().toString());
        System.out.println(reqBody.getFileName());

        try {
            return ocrService.doOcr(
                    ImageIO.read(new ByteArrayInputStream(
                            reqBody.getImage().getBytes())),
                    KancollePcImage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", e);
        }
    }

}
