package Entity.Characters;

import Entity.Collectible.GoldCollectible;
import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;

    public static int score = 0;
    private final int[] numFrames = {
            2, 8, 1, 2
    };
    //player stuff
    private int health;
    private int maxHealth;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;

    // animations
    private ArrayList<BufferedImage[]> sprites;


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
        killJumpStart = -7.7;
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

            for (int i = 0; i < 4; i++) {

                BufferedImage[] bi =
                        new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {


                    bi[j] = spritesheet.getSubimage( //res
                            j * width,
                            i * height,
                            width,
                            height
                    );
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

    public static int getScore() { return score; }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() { return health; }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setDead() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public void checkDead(Player p) {
        if (p.getHealth() == 0) {
            p.setDead();
        }
    }


    public void checkAttack(ArrayList<Enemy> enemies) {

        // loop through enemies
        for (int i = 0; i < enemies.size(); i++) {

            Enemy e = enemies.get(i);

            // check enemy collision
            if (e instanceof Slugger) {

                if (intersects(e) && this.falling) {

                    dy = killJumpStart;

                    e.health--;

                    setScore(getScore() + e.getWorth());

                } else if (intersects(e)) {
                    hit(e.getDamage());
                }

            } else if (e instanceof Shell) {

                if (intersects(e) && this.falling) {



                    dy = killJumpStart;

                    e.health--;

                    setScore(getScore() + e.getWorth());

                }
            }
        }
    }

    public void hit(int damage) {

        if (flinching) return;
        health -= damage;
        if (health < 0) health = 0;
        if (health == 0) dead = true;
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
        if (flinching) {
            long elapsed =
                    (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 1000) {
                flinching = false;
            }
        }

        //set animation

        if (dy > 0) {
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
        if (right) facingRight = true;
        if (left) facingRight = false;
    }


    public void checkCollect(ArrayList<GoldCollectible> goldCoins) {

        // loop through enemies
        for (int i = 0; i < goldCoins.size(); i++) {

            GoldCollectible goldCoin = goldCoins.get(i);

            // check enemy collision
            if (intersects(goldCoin)) {
                goldCoin.pickUp();
                goldCoin.dead = true;
            }
        }


    }

    public void draw(Graphics2D g) {

        setMapPosition();

        //draw player
        if (notOnScreen()) {
            this.dead = true;
        }
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
