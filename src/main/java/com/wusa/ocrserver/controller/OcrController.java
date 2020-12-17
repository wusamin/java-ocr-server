package com.wusa.ocrserver.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wusa.ocrserver.base.BaseImage;
import com.wusa.ocrserver.dto.OcrRequestBody;
import com.wusa.ocrserver.image.KancolleAndroid;
import com.wusa.ocrserver.service.OcrService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/ocr")
@Slf4j
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> test() {
        return Map.of("message", "ok");
    }

    @GetMapping(value = "/warmup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> warmUp(HttpServletResponse response) {
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/imagetest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object ocrTest() {
        try {
            return ocrService.doOcr(ImageIO.read(Paths.get("").toFile()),
                    KancolleAndroid.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", e);
        }
    }

    @SuppressWarnings("unchecked")
    @PostMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object doOcr(@ModelAttribute OcrRequestBody reqBody) {

        Class<? extends BaseImage> clazz;

        log.info("ImageType : " + reqBody.getImageType());
        log.info("FileName: " + reqBody.getFileName());

        try {
            clazz =
                (Class<? extends BaseImage>) Class.forName(
                        "com.wusa.ocrserver.image." + reqBody.getImageType());
        } catch (Exception e1) {
            e1.printStackTrace();
            return Map.of("error", "There is no such a class.");
        }

        try {
            return ocrService.doOcr(ImageIO.read(
                    new ByteArrayInputStream(reqBody.getImage().getBytes())),
                    clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", "an error has occured.");
        }
    }

}
