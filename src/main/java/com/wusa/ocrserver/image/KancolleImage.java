package com.wusa.ocrserver.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import com.wusa.ocrserver.base.BaseImage;
import com.wusa.ocrserver.base.RelationalDto;
import com.wusa.ocrserver.dto.TKancolleResorcesDto;

public class KancolleImage extends BaseImage {
    public KancolleImage(BufferedImage bi) {
        super(bi);
    }

    /** fuel coordinates */
    protected Rectangle fuel = new Rectangle(1015, 50, 80, 20);

    /** ammo coordinates */
    protected Rectangle ammo = new Rectangle(1015, 78, 80, 20);

    /** steel coordinates */
    protected Rectangle steel = new Rectangle(1120, 50, 80, 20);

    /** bauxite coordinates */
    protected Rectangle bauxite = new Rectangle(1120, 78, 80, 20);

    /** bucket coordinates */
    protected Rectangle bucket = new Rectangle(1037, 16, 60, 22);

    @Override
    public RelationalDto doOcr() {
        try {
            iTesseract.setTessVariable("tessedit_char_whitelist", "1234567890");
            TKancolleResorcesDto dto = new TKancolleResorcesDto();

            dto.setParam(getOcrResult(bufferedImage, fuel).get(0), "Fuel")
                    .setParam(getOcrResult(bufferedImage, ammo).get(0), "Ammo")
                    .setParam(getOcrResult(bufferedImage, steel).get(0),
                            "Steel")
                    .setParam(getOcrResult(bufferedImage, bauxite).get(0),
                            "Bauxite")
                    .setParam(getOcrResult(bufferedImage, bucket).get(0),
                            "Bucket");
            //            dto.setFileName(img.getFileName().toString());
            dto.setRecordFlg("0");
            dto.setInsDate(new Date());

            //            if (NumberUtils.isDigits(
            //                    removeFileExtension(img.getFileName().toString()))) {
            //                dto.setRecDate(generateRecDate(img));
            //            } else {
            //                dto.setRecDate(calculateFileTime(img));
            //            }

            //            dto.setHyperLink(master.get("PATH", "screanShot", env).paramFirst);

            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TKancolleResorcesDto();
    }
}
