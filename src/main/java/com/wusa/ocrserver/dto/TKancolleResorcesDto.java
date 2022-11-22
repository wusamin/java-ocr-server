package com.wusa.ocrserver.dto;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.wusa.ocrserver.base.RelationalDto;

import net.sourceforge.tess4j.Word;

public class TKancolleResorcesDto implements Serializable, RelationalDto {

    @Override
    public Class<? extends RelationalDto> returnClass() {
        return TKancolleResorcesDto.class;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getAmmo() {
        return ammo;
    }

    public void setAmmo(String ammo) {
        this.ammo = ammo;
    }

    public String getSteel() {
        return steel;
    }

    public void setSteel(String steel) {
        this.steel = steel;
    }

    public String getBauxite() {
        return bauxite;
    }

    public void setBauxite(String bauxite) {
        this.bauxite = bauxite;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
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

    public float getFuelConfidence() {
        return fuelConfidence;
    }

    public void setFuelConfidence(float fuelConfidence) {
        this.fuelConfidence = fuelConfidence;
    }

    public float getSteelConfidence() {
        return steelConfidence;
    }

    public void setSteelConfidence(float steelConfidence) {
        this.steelConfidence = steelConfidence;
    }

    public float getAmmoConfidence() {
        return ammoConfidence;
    }

    public void setAmmoConfidence(float ammoConfidence) {
        this.ammoConfidence = ammoConfidence;
    }

    public float getBauxiteConfidence() {
        return bauxiteConfidence;
    }

    public void setBauxiteConfidence(float bauxiteConfidence) {
        this.bauxiteConfidence = bauxiteConfidence;
    }

    public float getBucketConfidence() {
        return bucketConfidence;
    }

    public void setBucketConfidence(float bucketConfidence) {
        this.bucketConfidence = bucketConfidence;
    }

    public String getRecordFlg() {
        return recordFlg;
    }

    public void setRecordFlg(String recordFlg) {
        this.recordFlg = recordFlg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
