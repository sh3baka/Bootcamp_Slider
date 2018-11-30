package Entity.Effects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {

    public static BufferedImage[] sprites;
    private int x;
    private int y;
    private int xmap;
    private int ymap;
    private int width;
    private int height;
    private Animation animation;
    private boolean remove;

    public Explosion(int x, int y) {

        this.x = x;
        this.y = y;

        width = 33;
        height = 20;

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
        animation.setDelay(70);


    }

    public static BufferedImage[] getSprites() {
        return sprites;
    }

    public void update() {
        animation.update();
//        if (animation.hasPlayedOnce()) {
//            remove = true;
//        }
    }

    public boolean shoulRemove() {
        return remove;
    }

    public void setMapPosition(int x, int y) {
        xmap = x;
        ymap = y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(
                animation.getImage(),
                x + xmap - width / 2,
                y + ymap - height / 2,
                null
        );
    }
}
