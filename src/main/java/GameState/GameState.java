package GameState;

import Audio.AudioPlayer;
import Entity.Characters.*;
import Entity.Collectible.Collectible;
import Entity.Collectible.GoldCoin;
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

    Slugger slugger;
    Fly fly;
    Slime slime;
    Spike spike;
    GoldCoin c;

    ArrayList<Collectible> goldCoins;
    YellowKey yKey;
    ArrayList<ClosedDoor> closedDoors;
    ArrayList<OpenDoor> openDoors;

    AudioPlayer bgMusic;

    int stage;



    protected GameStateManager gsm;

    public void init(){

    }

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

    public void updateDoors() {
        for (int i = 0; i < closedDoors.size(); i++) {
            ClosedDoor d = closedDoors.get(i);
            //d.update();
            if (player.getKey()) {
                closedDoors.remove(i);
                i--;
                openDoors.add(
                        new OpenDoor(tileMap, d.getx(), d.gety()));
            }
        }
    }

    public void updateGoldCoins() {
        for (int i = 0; i < goldCoins.size(); i++) {
            goldCoins.get(i).update();
            if (goldCoins.get(i).isDead()) {
                goldCoins.remove(i);
            }
        }
    }

    public void updateShells() {
        for (int i = 0; i < shells.size(); i++) {
            Enemy e = shells.get(i);
            if (e.getHealth() == 0) {
                shells.remove(i);
                i--;
            }
        }
    }

    public void updateSlugs() {
        for (int i = 0; i < slugs.size(); i++) {
            Enemy e = slugs.get(i);
            e.update();
            if (e.getHealth() == 1) {
                slugs.remove(i);
                i--;
                shells.add(
                        new Shell(tileMap, e.getx(), e.gety()));
            }
        }
    }

    public void updateFlies(ArrayList<Enemy> flys) {
        for (int i = 0; i < flys.size(); i++) {
            Enemy e = flys.get(i);
            e.update();
            if (e.getHealth() == 0) {
                flys.remove(i);
                i--;
            }
        }
    }
}
