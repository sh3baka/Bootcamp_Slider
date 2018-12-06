package Entity.Characters;

import Audio.AudioPlayer;
import Entity.Collectible.Collectible;
import Entity.Collectible.YellowKey;
import Entity.Effects.Animation;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends MapObject {

    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    public static int score = 0;
    private static int coins;
    private static double X;
    private static double Y;
    private final int[] numFrames = {
            2, 8, 1, 2
    };
    private HashMap<String, AudioPlayer> sfx;
    //player stuff
    private int health;
    private int maxHealth;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;
    private boolean hasKey;
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
        killJumpStart = -5.6;
        stopJumpSpeed = 0.3;
        maxFallSpeed = 4.0;

        coins = 0;
        hasKey = false;

        facingRight = true;

        health = maxHealth = 3;

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

        sfx = new HashMap<String, AudioPlayer>();
        sfx.put("jump", new AudioPlayer("/SFX/jumpland44100.mp3"));

    }

    public static double getX() {
        return X;
    }

    public static double getY() {
        return Y;
    }

    public static int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean getKey() {
        return hasKey;
    }

    public void setKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setDead() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean checkDead(Player p) {
        if (p.getHealth() == 0) {
            p.setDead();
            return true;
        }
        return false;
    }




    public void checkAttack(ArrayList<Enemy> enemies) {

        // loop through enemies
        for (int i = 0; i < enemies.size(); i++) {

            Enemy e = enemies.get(i);

            // check enemy collision
            if (e instanceof Enemy) {

                if (intersects(e) && this.falling) {

                    dy = killJumpStart;
                    e.health--;
                    setScore(getScore() + e.getWorth());

                } else if (intersects(e)) {
                    hit(e.getDamage());
                }
            }
        }
    }

    public void checkKeys(YellowKey key) {
        if (intersects(key)) {
            setKey(true);
            key.dead = true;
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
            sfx.get("jump").play();
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

        X = x;
        Y = y;
    }


    public void checkCollect(ArrayList<Collectible> goldCoins) {

        // loop through enemies
        for (int i = 0; i < goldCoins.size(); i++) {

            Collectible goldCoin = goldCoins.get(i);

            // check enemy collision
            if (intersects(goldCoin)) {
                goldCoin.pickUp();
                goldCoin.dead = true;
                coins++;
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

    @Override
    public void setPosition(double x, double y) {
        super.x = x;
        super.y = y;
    }
}
