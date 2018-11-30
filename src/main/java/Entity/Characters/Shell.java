package Entity.Characters;

import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Shell extends Enemy {

    private BufferedImage[] sprites;

    public Shell(TileMap tm, int x, int y) {

        super(tm);

        this.x = x;
        this.y = y;

        width = 33;
        height = 20;
        cwidth = 30;
        cheight = 10;

        health = 1;
        worth = 5;

        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Enemies/snailShell.png"
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

    public void update() {

        //update position
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {

        //if(!notOnScreen()) return;

        setMapPosition();
        super.draw(g);

    }

}
