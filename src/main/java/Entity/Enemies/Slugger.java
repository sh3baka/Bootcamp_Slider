package Entity.Enemies;

import Entity.Enemy;
import TileMap.Tile;
import Entity.*;
import TileMap.TileMap;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Slugger extends Enemy {

    private BufferedImage[] sprites;

    public Slugger(TileMap tm) {

        super(tm);

        moveSpeed = 0.3;
        maxSpeed = 0.3;
        //fallSpeed = 0.2;
        //maxFallSpeed = 10.0;

        width = 54;
        height = 31;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 2;
        damage = 1;

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Enemies/snailWalk1.png"
                    )
            );
            sprites = new BufferedImage[1];
            for(int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(
                        i * width,
                        0,
                        width,
                        height
                );
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right = true;
        facingRight = false;

    }

    private void getNextPosition() {

        //movement
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        }

        //falling
        if(falling) {
            dy += fallSpeed;

        }

    }

    public void update() {

        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        if(flinching) {
            long elapsed =
                    (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) {
                flinching = false;
            }
        }

        //if it hits a wall, go other direction
        if (right && dx ==0) {
            right = false;
            left = true;
            facingRight = true;


        }
        else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = false;
        }

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {

        //if(notOnScreen()) return;

        setMapPosition();
        super.draw(g);

    }
}