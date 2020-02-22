package com.wusa.ocrserver.dto;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.wusa.ocrserver.base.RelationalDto;

import lombok.Data;
import net.sourceforge.tess4j.Word;

@Data
public class TKancolleResorcesDto implements Serializable, RelationalDto {

    @Override
    public Class<? extends RelationalDto> returnClass() {
        return TKancolleResorcesDto.class;
    }

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;

    /**
     * 燃料
     */
    private String fuel;

    /**
     * 弾薬
     */
    private String ammo;

    /**
     * 鋼材
     */
    private String steel;

    /**
     * ボーキサイト
     */
    private String bauxite;

    /**
     * 修復剤
     */
    private String bucket;

    /**
     * 記録日
     */
    private String recDate;

    /**
     * 画像ファイルへのハイパーリンク
     */
    private String hyperLink;

    /**
     * 登録日時
     */
    private Date insDate;

    /**
     * 検出率
     */
    private float fuelConfidence;

    /**
     * 検出率
     */
    private float steelConfidence;

    /**
     * 検出率
     */
    private float ammoConfidence;

    /**
     * 検出率
     */
    private float bauxiteConfidence;

    /**
     * 検出率
     */
    private float bucketConfidence;

    /**
     * マスタ転写済みフラグ
     */
    private String recordFlg;

    /**
     * ファイル名
     */
    private String fileName;

    /**
     * Wordクラスの値をキーに合わせたパラメータにセットする
     *
     * @param word
     * @param key
     * @return
     */
    public TKancolleResorcesDto setParam(Word word, String key) {
        TKancolleResorcesDto dto = this;
        String methodName =
            new StringBuilder().append("set").append(key).toString();
        try {
            Class<?> cls = dto.getClass();
            cls.getMethod(methodName, String.class).invoke(dto, word.getText());
            cls.getMethod(methodName + "Confidence", float.class)
                    .invoke(dto, word.getConfidence());
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
