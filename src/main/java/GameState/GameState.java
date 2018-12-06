package GameState;

import Audio.AudioPlayer;
import Entity.Characters.*;
import Entity.Collectible.Collectible;
import Entity.Collectible.YellowKey;
import Entity.Effects.ClosedDoor;
import Entity.Effects.OpenDoor;
import Entity.Hud.HUD;
import TileMap.TileMap;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class GameState {
    Player player;
    TileMap tileMap;
    Background bg;
    HUD hud;

    ArrayList<Enemy> slugs;
    ArrayList<Enemy> shells;
    ArrayList<Enemy> flys;
    ArrayList<Enemy> slimes;
    ArrayList<Enemy> spikes;



    ArrayList<Collectible> goldCoins;
    YellowKey yKey;
    ArrayList<ClosedDoor> closedDoors;
    ArrayList<OpenDoor> openDoors;

    AudioPlayer bgMusic;

    int stage;



    protected GameStateManager gsm;

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public void keyPressed(int k) {

        //arrows
        if (k == KeyEvent.VK_LEFT) player.setLeft(true);
        if (k == KeyEvent.VK_RIGHT) player.setRight(true);
        if (k == KeyEvent.VK_UP) player.setUp(true);
        if (k == KeyEvent.VK_DOWN) player.setDown(true);
        //wasd
        if (k == KeyEvent.VK_A) player.setLeft(true);
        if (k == KeyEvent.VK_D) player.setRight(true);
        if (k == KeyEvent.VK_W) player.setUp(true);
        if (k == KeyEvent.VK_S) player.setDown(true);
        //
        if (k == KeyEvent.VK_SPACE) player.setJumping(true);
        if (k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) player.setLeft(false);
        if (k == KeyEvent.VK_RIGHT) player.setRight(false);
        if (k == KeyEvent.VK_UP) player.setUp(false);
        if (k == KeyEvent.VK_DOWN) player.setDown(false);
        //wasd
        if (k == KeyEvent.VK_A) player.setLeft(false);
        if (k == KeyEvent.VK_D) player.setRight(false);
        if (k == KeyEvent.VK_W) player.setUp(false);
        if (k == KeyEvent.VK_S) player.setDown(false);
        //
        if (k == KeyEvent.VK_SPACE) player.setJumping(false);
    }

    void initEnemyArrayLists() {
        slugs = new ArrayList<Enemy>();
        shells = new ArrayList<Enemy>();
        flys = new ArrayList<Enemy>();
        slimes = new ArrayList<Enemy>();
        spikes = new ArrayList<Enemy>();
    }


}
