package com.wusa.spotify;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@SpringBootTest
class OcrServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetWord() {
        ITesseract tess = new Tesseract();
        // ① tessdataフォルダのパス指定
        tess.setDatapath("E:\\ProgramFiles\\git\\warehouse\\tessdata");
        tess.setLanguage("eng");
        // ② 画像ファイルのパス
        File target =
            new File(
                    "J:\\ProgramFiles\\Admiral_s_Desk\\SS\\2018082823524864.png");
        try {
            BufferedImage bi = ImageIO.read(target);

            List word = tess.getWords(bi, TessPageIteratorLevel.RIL_BLOCK);
            String doOcr = tess.doOCR(bi);
            System.out.println(word);
            System.out.println(doOcr);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

}
