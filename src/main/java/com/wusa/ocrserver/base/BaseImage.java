package com.wusa.ocrserver.base;

import static net.sourceforge.tess4j.util.ImageHelper.getSubImage;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import lombok.Setter;
import net.coobird.thumbnailator.Thumbnails;
import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
import net.sourceforge.tess4j.util.ImageHelper;

public abstract class BaseImage {
    @Setter
    protected Path img;

    @Setter
    protected String env;

    @Setter
    protected BufferedImage bufferedImage;

    protected ITesseract iTesseract = new Tesseract();

    public BaseImage(Path path) {
        img = path;
    }

    public BaseImage(BufferedImage bi) {
        bufferedImage = bi;
    }

    private DateTimeFormatter dateTimeFormat =
        DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public abstract RelationalDto doOcr();

    /**
     * OCRの結果がない場合はからのリストを返却する
     *
     * @param img
     * @param rect
     * @return
     * @throws IOException
     */
    protected List<Word> getOcrResult(Path img, Rectangle rect)
            throws IOException {
        List<Word> newList =
            iTesseract.getWords(zoomPict(getSubImg(img, rect)),
                    TessPageIteratorLevel.RIL_TEXTLINE);

        if (newList.size() > 0) {
            return newList;
        } else {
            return new ArrayList<Word>();
        }
    }

    /**
     * OCRの結果がない場合はからのリストを返却する
     *
     * @param img
     * @param rect
     * @return
     * @throws IOException
     */
    protected List<Word> getOcrResult(BufferedImage bufferedImage,
            Rectangle rect) throws IOException {
        List<Word> newList =
            iTesseract.getWords(zoomPict(getSubImg(bufferedImage, rect)),
                    TessPageIteratorLevel.RIL_TEXTLINE);

        if (newList.size() > 0) {
            return newList;
        } else {
            return new ArrayList<Word>();
        }
    }

    /**
     * Pathと座標矩形からバッファイメージを作成する
     *
     * @param img
     * @param rect
     * @return
     */
    protected BufferedImage getSubImg(Path img, Rectangle rect) {
        try {
            return getSubImage(ImageIO.read(
                    img.toFile()), rect.x, rect.y, rect.width, rect.height);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * BufferedImageを座標矩形でくり抜く
     * 
     * @param bi
     * @param rect
     * @return
     */
    protected BufferedImage getSubImg(BufferedImage bi, Rectangle rect) {
        return ImageHelper
                .getSubImage(bi, rect.x, rect.y, rect.width, rect.height);
    }

    private BufferedImage zoomPict(BufferedImage bi) throws IOException {
        final int expantRate = 1;
        return Thumbnails.of(bi)
                .size(bi.getWidth() * expantRate, bi.getHeight() * expantRate)
                .asBufferedImage();
    }

    /**
     * ファイルの更新日時が午前7時までの場合は前日の日付を返す
     *
     * @param path
     * @return
     */
    protected String calculateFileTime(Path path) {
        try {
            final SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
            final SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy/MM/dd");
            final long fileTimeMills =
                Files.getLastModifiedTime(path).toMillis();

            // 前日の画像を翌日の7時に処理するため、7時までの画像を前日のものとして扱う
            if (Integer.valueOf(hourFormat.format(fileTimeMills)) < 7) {
                return LocalDate
                        .parse(dateFormat.format(fileTimeMills), dateTimeFormat)
                        .minusDays(1)
                        .toString();
            } else {
                return dateFormat.format(fileTimeMills);
            }

        } catch (IOException e) {
            e.printStackTrace();
            // エラー起きたら転けなければいけないため、throwでよいのでは
            return new String();
        }
    }

    protected String generateRecDate(Path img) {
        String fileName = img.getFileName().toString();
        LocalDate fileDate =
            LocalDate.of(Integer.valueOf(StringUtils.substring(fileName, 0, 4)),
                    Integer.valueOf(StringUtils.substring(fileName, 4, 6)),
                    Integer.valueOf(StringUtils.substring(fileName, 6, 8)));
        // 前日の画像を翌日の7時に処理するため、7時までの画像を前日のものとして扱う
        if (Integer.valueOf(StringUtils.substring(fileName, 8, 10)) < 7) {
            return fileDate.minusDays(1).toString();
        }
        return fileDate.toString();

    }

    public static String removeFileExtension(String filename) {
        int lastDotPos = filename.lastIndexOf('.');

        if (lastDotPos == -1) {
            return filename;
        } else if (lastDotPos == 0) {
            return filename;
        } else {
            return filename.substring(0, lastDotPos);
        }
    }
}
