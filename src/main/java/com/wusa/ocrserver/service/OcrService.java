package com.wusa.ocrserver.service;

import static net.sourceforge.tess4j.util.ImageHelper.getSubImage;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.wusa.ocrserver.base.BaseImage;
import com.wusa.ocrserver.base.RelationalDto;
import com.wusa.ocrserver.dto.TKancolleResorcesDto;

import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

@Service
public class OcrService {

    public RelationalDto doOcr(BufferedImage bufferedImage,
            Class<? extends BaseImage> clazz) {
        try {
            BaseImage image =
                clazz.getDeclaredConstructor(BufferedImage.class)
                        .newInstance(bufferedImage);
            return image.doOcr();
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void test() {
        ITesseract iTesseract = new Tesseract();

        iTesseract.setDatapath("E:/ProgramFiles/git/warehouse/tessdata");

        Rectangle fuel = new Rectangle(1015, 50, 80, 20);
        Rectangle ammo = new Rectangle(1015, 77, 80, 20);
        Rectangle steel = new Rectangle(1121, 50, 77, 20);
        Rectangle bauxite = new Rectangle(1121, 77, 77, 20);
        Rectangle bucket = new Rectangle(1037, 16, 60, 22);

        Path path =
            Paths.get(
                    "J:\\ProgramFiles\\Admiral_s_Desk\\SS\\20180822224149229.png");
        TKancolleResorcesDto dto = new TKancolleResorcesDto();

        try {
            BufferedImage b = ImageIO.read(path.toFile());
            BufferedImage bi = getSubImg(path, fuel);
            System.out.println(ImageIO
                    .write(bi, "png", new File("E:/Picture/temp/test.ping")));
            System.out.println(MessageFormat.format("ocr:{0}",
                    iTesseract.doOCR(ImageIO.read(path.toFile()))));
        } catch (TesseractException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Word> l =
            iTesseract.getWords(getSubImg(path, fuel),
                    TessPageIteratorLevel.RIL_TEXTLINE);

        Word fuelRes =
            iTesseract
                    .getWords(getSubImg(path, fuel),
                            TessPageIteratorLevel.RIL_TEXTLINE)
                    .get(0);
        Word ammoRes =
            iTesseract
                    .getWords(getSubImg(path, ammo),
                            TessPageIteratorLevel.RIL_TEXTLINE)
                    .get(0);
        dto.setParam(fuelRes, "Fuel").setParam(ammoRes, "Ammo");
        System.out.println(MessageFormat.format("fuel:{0}", dto.getFuel()));
        System.out.println(MessageFormat.format("ammo:{0}", dto.getAmmo()));
    }

    /**
     * Pathと座標矩形からバッファイメージを作成する
     *
     * @param img
     * @param rect
     * @return
     */
    private BufferedImage getSubImg(Path img, Rectangle rect) {
        try {
            return getSubImage(ImageIO.read(
                    img.toFile()), rect.x, rect.y, rect.width, rect.height);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
