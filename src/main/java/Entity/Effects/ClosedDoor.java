package Entity.Effects;

import Entity.Characters.MapObject;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ClosedDoor extends MapObject {

    private BufferedImage[] sprites;

    public ClosedDoor(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        cwidth = 30;
        cheight = 30;

        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Tiles/door_closed.png"
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
    public void update() {
        //update position
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        animation.update();
    }
}
