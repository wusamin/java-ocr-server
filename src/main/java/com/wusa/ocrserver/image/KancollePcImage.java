package com.wusa.ocrserver.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class KancollePcImage extends KancolleImage {
    public KancollePcImage(BufferedImage bi) {
        super(bi);

        /** 燃料 */
        fuel = new Rectangle(1015, 50, 80, 20);

        /** 弾薬座標 */
        ammo = new Rectangle(1015, 78, 80, 20);

        /** 鋼材座標 */
        steel = new Rectangle(1120, 50, 80, 20);

        /** ボーキサイト座標 */
        bauxite = new Rectangle(1120, 78, 80, 20);

        /** 修復剤座標 */
        bucket = new Rectangle(1037, 16, 60, 22);
    }
}
