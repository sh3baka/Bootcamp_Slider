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
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchTime;

    //fireball
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    //private ArrayList<FireBall> fireBalls;

    //scratch
    private boolean scratching;
    private int scratchDamage;
    private int scratchRange;

    //glinding
    private boolean gliding;

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
    }

}
