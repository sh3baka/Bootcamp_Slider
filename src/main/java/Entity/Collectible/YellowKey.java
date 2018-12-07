package Entity.Collectible;

import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class YellowKey extends Collectible {

    private BufferedImage[] sprites;

    public YellowKey(TileMap tm) {
        super(tm);

        width = 70;
        height = 70;
        cwidth = 20;
        cheight = 10;

        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Items/keyYellow.png"
                    )
            );
            sprites = new BufferedImage[1];
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
        animation.setDelay(300);
    }

    public void draw(Graphics2D g) {
        setMapPosition();
        super.draw(g);
    }
}
