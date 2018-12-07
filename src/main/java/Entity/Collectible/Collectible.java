package Entity.Collectible;

import Entity.Characters.Enemy;
import Entity.Characters.Player;
import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Collectible extends Enemy {
    public boolean dead;
    protected int score;
    private BufferedImage[] sprites;

    public Collectible(TileMap tm) {
        super(tm);

//        fallSpeed = 0.2;
//        maxFallSpeed = 10.0;

        width = 30;
        height = 30;
        cwidth = 10;
        cheight = 10;

        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Items/coinGold.png"
                    )
            );
            sprites = new BufferedImage[4];
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
        animation.setDelay(200);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void pickUp() {

        Player.score += getScore();

    }

    private void getNextPosition() {

        //falling
        if (falling) {
            dy += fallSpeed;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void update() {
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        animation.update();
    }

    public void draw(Graphics2D g) {

        //if(notOnScreen()) return;

        setMapPosition();
        super.draw(g);

    }
}
