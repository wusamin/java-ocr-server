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

import com.wusa.ocrserver.base.BaseImage;
import com.wusa.ocrserver.base.RelationalDto;
import com.wusa.ocrserver.dto.OcrRequestBody;
import com.wusa.ocrserver.image.KancollePcImage;
import com.wusa.ocrserver.service.OcrService;

@RestController
@RequestMapping(value = "/ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> test() {
        return Map.of("message", "ok");
    }

    @RequestMapping(value = "/ocrtest", produces = MediaType.APPLICATION_JSON_VALUE)
    public RelationalDto ocrTest() {

        return ocrService.ocrTest();
    }

    @PostMapping(value = "/imagetest", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @SuppressWarnings("unchecked")
    @PostMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object doOcr2(@ModelAttribute OcrRequestBody reqBody) {

        Class<? extends BaseImage> clazz;

        try {
            clazz =
                (Class<? extends BaseImage>) Class
                        .forName(reqBody.getImageType());
            if (!(clazz instanceof Class)) {

            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return Map.of("error", e1, "result", "There is no such a class.");
        }

        try {
            return ocrService.doOcr(ImageIO.read(
                    new ByteArrayInputStream(reqBody.getImage().getBytes())),
                    clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", e);
        }
    }

}
