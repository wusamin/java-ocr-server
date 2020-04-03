package com.wusa.ocrserver.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class KancolleAndroid extends KancolleImage {
    public KancolleAndroid(BufferedImage bi) {
        super(bi);

        /** 燃料 */
        fuel = new Rectangle(1671, 77, 111, 32);

        /** 弾薬座標 */
        ammo = new Rectangle(1671, 117, 111, 32);

        /** 鋼材座標 */
        steel = new Rectangle(1830, 77, 111, 32);

        /** ボーキサイト座標 */
        bauxite = new Rectangle(1830, 117, 111, 32);

        /** 修復剤座標 */
        bucket = new Rectangle(1700, 26, 87, 32);
    }
}
