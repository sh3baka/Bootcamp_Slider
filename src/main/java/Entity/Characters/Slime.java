package Entity.Characters;

import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Slime extends Enemy {


    private BufferedImage[] sprites;

    public Slime(TileMap tm) {

        super(tm);

        moveSpeed = 0.1;
        maxSpeed = 0.1;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 33;
        height = 20;
        cwidth = 30;
        cheight = 10;

        health = maxHealth = 1;
        damage = 2;
        worth = 15;

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Enemies/slimeWalk1.png"
                    )
            );
            sprites = new BufferedImage[11];
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
        if (falling) {
            dy += fallSpeed;

        }
    }

    public void update() {

        //update position
        flinching();

        //if it hits a wall, go other direction
        if (right && dx == 0) {
            right = false;
            left = true;
            facingRight = true;
        } else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = false;
        }

        //update animation
        animation.update();
    }

    private void flinching() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        if (flinching) {
            long elapsed =
                    (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) {
                flinching = false;
            }
        }
    }

    public void draw(Graphics2D g) {

        //if(!notOnScreen()) return;

        setMapPosition();
        super.draw(g);

    }
}


