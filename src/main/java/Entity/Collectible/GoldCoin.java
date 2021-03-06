package Entity.Collectible;

import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GoldCoin extends Collectible {

    private BufferedImage[] sprites;

    public GoldCoin(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        cwidth = 10;
        cheight = 10;

        score = 100;

        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Items/coinGold.png"
                    )
            );
            sprites = new BufferedImage[10];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(
                        i * width,
                        0,
                        width,
                        height
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(100);
    }
}
