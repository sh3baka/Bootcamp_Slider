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
    static int coins = 0;
    private static double X;
    private static double Y;
    private static double dY;
    private final int[] numFrames = {
            16, 6, 1, 2
    };
    private HashMap<String, AudioPlayer> sfx;
    private HashMap<String, AudioPlayer> sfx2;
    private HashMap<String, AudioPlayer> sfx3;
    private HashMap<String, AudioPlayer> sfx4;
    private HashMap<String, AudioPlayer> sfx5;
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
        stopJumpSpeed = 0.3;
        maxFallSpeed = 4.0;

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
        animation.setDelay(50);

        sfx = new HashMap<String, AudioPlayer>();
        sfx.put("jump", new AudioPlayer("/SFX/Boing-sound.mp3"));

        sfx2 = new HashMap<String, AudioPlayer>();
        sfx2.put("damaged", new AudioPlayer("/SFX/Boing-sound-effect.mp3"));

        sfx3 = new HashMap<String, AudioPlayer>();


        sfx4 = new HashMap<String, AudioPlayer>();
        sfx4.put("key", new AudioPlayer("/SFX/key.mp3"));

        sfx5 = new HashMap<String, AudioPlayer>();
        sfx5.put("damage", new AudioPlayer("/SFX/damage.mp3"));
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
        Player.score = score;
    }

    public static int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        Player.coins = coins;
    }

    public boolean getKey() {
        return hasKey;
    }

    void setKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    boolean hasKey() {
        return hasKey;
    }

    public void checkAttack(ArrayList<Enemy> enemies) {

        //  loop through enemies
        for (Enemy e : enemies) {

            // check enemy collision
            if (intersects(e) && this.dy > 0) {
                sfx2.get("damaged").play();
                dy = jumpStart;
                e.health--;
                setScore(getScore() + e.getWorth());

            } else if (intersects(e)) {
                hit(e.getDamage());
            }

        }
    }

    public void checkSpikes(ArrayList<Enemy> spikes) {

        // loop through enemies
        for (Enemy e : spikes) {

            // check enemy collision
            if (intersects(e)) {

                dy = jumpStart;
                hit(e.getDamage());
            }
        }
    }

    public void checkKeys(YellowKey key) {
        if (intersects(key)) {
            sfx4.get("key").play();
            setKey(true);
            key.dead = true;
        }
    }

    void hit(int damage) {
        if (flinching) return;
        sfx5.get("damage").play();
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
                animation.setDelay(70);
                width = 30;
            }
        } else {
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(100);
                width = 30;
            }
        }
        if (y > 260) {
            setHealth(0);
        }
        animation.update();

        //set direction
        if (right) facingRight = true;
        if (left) facingRight = false;

        X = x;
        Y = y;
        dY = dy;
    }

    public void checkCollect(ArrayList<Collectible> goldCoins) {

        // loop through enemies
        for (Collectible c : goldCoins) {

            // check enemy collision
            if (intersects(c)) {
                sfx3.put("coin", new AudioPlayer("/SFX/coin.mp3"));
                sfx3.get("coin").play();
                c.pickUp();
                c.dead = true;
                coins++;
            }
        }
    }

    public void draw(Graphics2D g) {
        setMapPosition();

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
