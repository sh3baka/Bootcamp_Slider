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
    static AudioPlayer bgMusic;
    static AudioPlayer menuBgMusic;
    protected GameStateManager gsm;
    Player player;
    TileMap tileMap;
    Background background;
    HUD hud;
    ArrayList<Enemy> slugs;
    ArrayList<Enemy> shells;
    ArrayList<Enemy> flys;
    ArrayList<Enemy> slimes;
    ArrayList<Enemy> spikes;
    ArrayList<Collectible> goldCoins;
    LinkedList<Point> spikePoints;
    LinkedList<Point> sluggerPoints;
    LinkedList<Point> flyPoints;
    LinkedList<Point> slimePoints;
    Slugger slugger;
    Fly fly;
    Slime slime;
    Spike spike;
    GoldCoin c;
    YellowKey yKey;
    ArrayList<ClosedDoor> closedDoors;
    ArrayList<OpenDoor> openDoors;
    AudioPlayer gameOverMusic;
    int stage;

    public void init() {

    }

    public void update() {
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

        if (k == KeyEvent.VK_SPACE) player.setJumping(false);
    }

    void initEnemyArrayLists() {
        slugs = new ArrayList<Enemy>();
        shells = new ArrayList<Enemy>();
        flys = new ArrayList<Enemy>();
        slimes = new ArrayList<Enemy>();
        spikes = new ArrayList<Enemy>();

        openDoors = new ArrayList<OpenDoor>();
        closedDoors = new ArrayList<ClosedDoor>();
    }

    void initPointLists() {
        sluggerPoints = new LinkedList<Point>();
        flyPoints = new LinkedList<Point>();
        slimePoints = new LinkedList<Point>();
        spikePoints = new LinkedList<Point>();
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

    public void updateDeath() {
        if (player.checkDead(player)) {
            gameOverMusic.play();
            bgMusic.stop();
            player.setScore(0);
            player.setCoins(0);
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
    }

    public void updateCollisions() {
        player.checkCollect(goldCoins);
        player.checkKeys(yKey);

        background.setPosition(tileMap.getx(), tileMap.gety());

        player.checkAttack(slugs);
        player.checkAttack(shells);
        player.checkAttack(flys);
        player.checkAttack(slimes);
        player.checkSpikes(spikes);

        updateFlies(flys);
        updateFlies(slimes);
        updateSlugs();
        updateShells();
        updateGoldCoins();
        updateDoors();
    }

    public void drawPrettyMap(Graphics2D g) {
        background.draw(g);
        tileMap.draw(g);

        for (ClosedDoor door : closedDoors) {
            door.draw(g);
        }
        for (OpenDoor door : openDoors) {
            door.draw(g);
        }

        player.draw(g);
        hud.draw(g);

        if (!yKey.isDead()) {
            yKey.draw(g);
        }

        drawEnemies(g, slugs);
        drawEnemies(g, shells);
        drawEnemies(g, flys);
        drawEnemies(g, slimes);
        drawEnemies(g, spikes);
        drawThings(g, goldCoins);
    }
}
