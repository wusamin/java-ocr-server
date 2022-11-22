package com.wusa.ocrserver.dto;

import java.io.Serializable;
import java.util.Date;

import com.wusa.ocrserver.base.RelationalDto;

public class TAzurlaneResourceDto implements Serializable, RelationalDto {

    @Override
    public Class<? extends RelationalDto> returnClass() {
        return TAzurlaneResourceDto.class;
    }

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

    public String getFileName() {
        return fileName;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public float getFuelConfidence() {
        return fuelConfidence;
    }

    public void setFuelConfidence(float fuelConfidence) {
        this.fuelConfidence = fuelConfidence;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public float getMoneyConfidence() {
        return moneyConfidence;
    }

    public void setMoneyConfidence(float moneyConfidence) {
        this.moneyConfidence = moneyConfidence;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public float getDiamondConfidence() {
        return diamondConfidence;
    }

    public void setDiamondConfidence(float diamondConfidence) {
        this.diamondConfidence = diamondConfidence;
    }

    public String getHyperLink() {
        return hyperLink;
    }

    public void setHyperLink(String hyperLink) {
        this.hyperLink = hyperLink;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getRecordFlg() {
        return recordFlg;
    }

    public void setRecordFlg(String recordFlg) {
        this.recordFlg = recordFlg;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
