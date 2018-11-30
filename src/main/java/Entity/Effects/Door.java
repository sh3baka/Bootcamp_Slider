package Entity.Effects;

import Entity.Characters.MapObject;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends MapObject {

    private BufferedImage door;

    public Door(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        cwidth = 30;
        cheight = 30;

        try {

            door = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Tiles/door_closed.png"

                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {

        //if(!notOnScreen()) return;

        setMapPosition();
        g.drawImage(door, 50, 50, null);

    }
}
