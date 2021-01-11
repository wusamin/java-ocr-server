package com.wusa.ocrserver.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class KancolleAndroid extends KancolleImage {
    public KancolleAndroid(BufferedImage bi) {
        super(bi);

        /** 燃料 */
        fuel = new Rectangle(1545, 71, 100, 33);

        /** 弾薬座標 */
        ammo = new Rectangle(1545, 110, 100, 30);

        /** 鋼材座標 */
        steel = new Rectangle(1690, 71, 100, 33);

        /** ボーキサイト座標 */
        bauxite = new Rectangle(1690, 110, 111, 32);

        /** 修復剤座標 */
        bucket = new Rectangle(1569, 23, 75, 30);
    }
}
