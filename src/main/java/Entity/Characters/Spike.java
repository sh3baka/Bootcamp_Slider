package Entity.Characters;

import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Spike extends Enemy {

    private BufferedImage[] sprites;

    public Spike(TileMap tm) {

        super(tm);

        width = 30;
        height = 30;
        cwidth = 25;
        cheight = 20;

        health = 1;
        worth = 0;
        damage = 1;

        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Items/spikes.png"
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
        animation.setDelay(30);

    }

    public void draw(Graphics2D g) {

        //if(!notOnScreen()) return;

        setMapPosition();
        super.draw(g);

    }

}
