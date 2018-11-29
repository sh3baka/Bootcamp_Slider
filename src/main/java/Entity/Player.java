package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

    //player stuff
    private int health;
    private int maxHealth;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;
    public static int score = 0;

    // animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
            2, 8, 1, 2, 4, 2, 5
    };

    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6; //res

    public Player(TileMap tm) {

        super(tm);

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;
        maxFallSpeed = 4.0;

        facingRight = true;

        health = maxHealth = 5;

        //load sprites
        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Player/playersprites.png"
                    )
            );

            sprites = new ArrayList<BufferedImage[]>();

            for (int i = 0; i < 7; i++) {

                BufferedImage[] bi =
                        new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {

                    if (i != 6) {
                        bi[j] = spritesheet.getSubimage( //res
                                j * width,
                                i * height,
                                width,
                                height
                        );

                    } else {
                        bi[j] = spritesheet.getSubimage( //res
                                j * width * 2,
                                i * height,
                                width,
                                height
                        );
                    }

                }

                sprites.add(bi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(600);

    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public static String getScore(){
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void checkAttack(ArrayList<Enemy> enemies) {

        // loop through enemies
        for(int i = 0; i < enemies.size(); i++) {

            Enemy e = enemies.get(i);

            // scratch attack
//            if(scratching) {
//                if(facingRight) {
//                    if(
//                            e.getx() > x &&
//                                    e.getx() < x + scratchRange &&
//                                    e.gety() > y - height / 2 &&
//                                    e.gety() < y + height / 2
//                    ) {
//                        e.hit(scratchDamage);
//                    }
//                }
//                else {
//                    if(
//                            e.getx() < x &&
//                                    e.getx() > x - scratchRange &&
//                                    e.gety() > y - height / 2 &&
//                                    e.gety() < y + height / 2
//                    ) {
//                        e.hit(scratchDamage);
//                    }
//                }
//            }
//
//            // fireballs
//            for(int j = 0; j < fireBalls.size(); j++) {
//                if(fireBalls.get(j).intersects(e)) {
//                    e.hit(fireBallDamage);
//                    fireBalls.get(j).setHit();
//                    break;
//                }
//            }

            // check enemy collision
            if(intersects(e) && this.falling) {
                e.dead = true;
            } else if(intersects(e)){
                hit(e.getDamage());
            }
        }
    }

    public void hit(int damage) {

        if(flinching) return;
        health -= damage;
        if(health < 0) health = 0;
        if(health == 0) dead = true;
        flinching = true;
        flinchTimer = System.nanoTime();

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
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        //cannot move while attacking, except in air
        if (
                (currentAction == SCRATCHING || currentAction == FIREBALL) &&
                        !(jumping || falling)) {
            dx = 0;
        }

        //jumping
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        //falling
        if (falling) {

            dy += fallSpeed;

            if (dy > 0) jumping = false;
            if (dy < 0 && !jumping) dy += stopJumpSpeed;
            if (dy > maxFallSpeed) dy = maxFallSpeed;

        }
    }

    public void update() {

        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check done flinching
        if(flinching) {
            long elapsed =
                    (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 1000) {
                flinching = false;
            }
        }

        //set animation

         if(dy > 0) {
             if (currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(100);
                width = 30;
            }
        } else if (dy < 0) {
            if (currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(-1);
                width = 30;
            }
        } else if (left || right) {
            if (currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }
        } else {
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }
        }
        animation.update();

        //set direction
        if (currentAction != SCRATCHING && currentAction != FIREBALL)
            if (right) facingRight = true;
        if (left) facingRight = false;
    }


    public void checkCollect(ArrayList<Coin> coins) {

        // loop through enemies
        for (int i = 0; i < coins.size(); i++) {

            Coin coin = coins.get(i);

            // check enemy collision
            if (intersects(coin)) {
                coin.pickUp();
                coin.dead = true;
            }
        }


    }


    public void draw(Graphics2D g) {

        setMapPosition();

        //draw player
        if (flinching) {
            long elapsed =
                    (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed / 100 % 2 == 0) {
                return;
            }
        }
        super.draw(g);
    }
}
