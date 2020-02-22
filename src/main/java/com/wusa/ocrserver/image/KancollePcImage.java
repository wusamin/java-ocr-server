package com.wusa.ocrserver.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class KancollePcImage extends KancolleImage {

    public KancollePcImage(Path path) {
        super(path);

        /** 燃料 */
        // 旧座標 new Rectangle(680, 30, 50, 30);
        fuel = new Rectangle(1015, 50, 80, 20);

        /** 弾薬座標 */
        // new Rectangle(680, 50, 50, 30)
        ammo = new Rectangle(1015, 78, 80, 20);

        /** 鋼材座標 */
        //new Rectangle(750, 30, 48, 30)
        steel = new Rectangle(1120, 50, 80, 20);

        /** ボーキサイト座標 */
        //new Rectangle(750, 50, 48, 30);
        bauxite = new Rectangle(1120, 78, 80, 20);

        /** 修復剤座標 */
        //new Rectangle(690, 10, 40, 20)
        bucket = new Rectangle(1037, 16, 60, 22);
    }

    public KancollePcImage(BufferedImage bi) {
        super(bi);

        /** 燃料 */
        // 旧座標 new Rectangle(680, 30, 50, 30);
        fuel = new Rectangle(1015, 50, 80, 20);

        /** 弾薬座標 */
        // new Rectangle(680, 50, 50, 30)
        ammo = new Rectangle(1015, 78, 80, 20);

        /** 鋼材座標 */
        //new Rectangle(750, 30, 48, 30)
        steel = new Rectangle(1120, 50, 80, 20);

        /** ボーキサイト座標 */
        //new Rectangle(750, 50, 48, 30);
        bauxite = new Rectangle(1120, 78, 80, 20);

        /** 修復剤座標 */
        //new Rectangle(690, 10, 40, 20)
        bucket = new Rectangle(1037, 16, 60, 22);
    }
}
