package com.wusa.ocrserver.dto;

import java.io.Serializable;
import java.util.Date;

import com.wusa.ocrserver.base.RelationalDto;

import lombok.Data;

@Data
public class TAzurlaneResourceDto implements Serializable, RelationalDto {

    @Override
    public Class<? extends RelationalDto> returnClass() {
        return TAzurlaneResourceDto.class;
    }

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * ファイル名
     */
    private String fileName;

    /**
     * 記録日
     */
    private String recDate;

    /**
     * 燃料
     */
    private String fuel;

    /**
     * 検出率
     */
    private float fuelConfidence;

    /**
     * お金
     */
    private String money;

    /**
     * 検出率
     */
    private float moneyConfidence;

    /**
     * ダイヤ（石）
     */
    private String diamond;

    /**
     * 検出率
     */
    private float diamondConfidence;

    /**
     * 画像ファイルへのハイパーリンク
     */
    private String hyperLink;

    /**
     * 登録日時
     */
    private Date insDate;

    /**
     * マスタ転写済みフラグ
     */
    private String recordFlg;

}
