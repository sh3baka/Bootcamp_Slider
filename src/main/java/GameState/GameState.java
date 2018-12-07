package GameState;

import Audio.AudioPlayer;
import Entity.Characters.*;
import Entity.Collectible.Collectible;
import Entity.Collectible.GoldCoin;
import Entity.Collectible.YellowKey;
import Entity.Effects.ClosedDoor;
import Entity.Effects.OpenDoor;
import Entity.Hud.HUD;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameState {
    protected GameStateManager gsm;
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
    static AudioPlayer bgMusic;
    AudioPlayer gameOverMusic;
    static AudioPlayer menuBgMusic;
    int stage;

    public void init() {

    }

    public void update(){
        //update player death
        if (player.checkDead(player)) {
            bgMusic.stop();
            gameOverMusic.play();
            player.setScore(0);
            player.setCoins(0);
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
    }

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
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
            Level1State.bgMusic.stop();
            Level2State.bgMusic.stop();
            Level3State.bgMusic.stop();
        }
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



    public void addSlimesToList(LinkedList<Point> slimePoints) {
        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
    }


    public void addFlysToList(LinkedList<Point> flyPoints) {
        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }
    }

    public void addSluggersToList(LinkedList<Point> sluggerPoints) {
        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }
    }

    public void addSpikesToList(LinkedList<Point> spikePoints) {
        for (Point point : spikePoints) {
            spike = new Spike(tileMap);
            spike.setPosition(point.x, point.y);
            spikes.add(spike);
        }
    }


    public void drawEnemies(Graphics2D g, ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    public void drawThings(Graphics2D g, ArrayList<Collectible> things) {
        for (Collectible c : things) {
            c.draw(g);
        }
    }

    public void updateCollisions(){
        //coin collect
        player.checkCollect(goldCoins);
        //keys
        player.checkKeys(yKey);
        //set background
        bg.setPosition(tileMap.getx(), tileMap.gety());

        //attack slugs
        player.checkAttack(slugs);

        //attack shells
        player.checkAttack(shells);

        //attack flies
        player.checkAttack(flys);

        //attack slimes
        player.checkAttack(slimes);

        //attack spikes
        player.checkSpikes(spikes);

        //update flies
        updateFlies(flys);
        //update slimes
        updateFlies(slimes);
        //update slugs
        updateSlugs();
        //update shells
        updateShells();
        //update goldCoins
        updateGoldCoins();
        //update doors
        updateDoors();
    }

    public void drawPrettyMap(Graphics2D g){
        //background and map
        bg.draw(g);
        tileMap.draw(g);
        //doors
        for (ClosedDoor door : closedDoors) {
            door.draw(g);
        }
        for (OpenDoor door : openDoors) {
            door.draw(g);
        }
        //player
        player.draw(g);
        hud.draw(g);
        //key
        if (!yKey.isDead()) {
            yKey.draw(g);
        }
        //draw stuff
        drawEnemies(g, slugs);
        drawEnemies(g, shells);
        drawEnemies(g, flys);
        drawEnemies(g, slimes);
        drawEnemies(g, spikes);
        drawThings(g, goldCoins);
    }
}
