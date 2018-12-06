package GameState;

import Audio.AudioPlayer;
import Entity.Characters.*;
import Entity.Collectible.Collectible;
import Entity.Collectible.GoldCoin;
import Entity.Collectible.YellowKey;
import Entity.Effects.ClosedDoor;
import Entity.Effects.OpenDoor;
import Entity.Hud.HUD;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class Level1State extends GameState {

    Player player;
    private TileMap tileMap;
    private Background bg;
    private HUD hud;

    private ArrayList<Enemy> slugs;
    private ArrayList<Enemy> shells;
    private ArrayList<Enemy> flys;
    private ArrayList<Enemy> slimes;

    private ArrayList<Collectible> goldCoins;
    private YellowKey yKey;
    private ArrayList<ClosedDoor> closedDoors;
    private ArrayList<OpenDoor> openDoors;

    private AudioPlayer bgMusic;

    private int stage;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();

    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1.png");
        tileMap.loadMap("/levelMap1.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.06);

        slugs = new ArrayList<Enemy>();
        shells = new ArrayList<Enemy>();
        flys = new ArrayList<Enemy>();
        slimes = new ArrayList<Enemy>();

        goldCoins = new ArrayList<Collectible>();

        bg = new Background("/Backgrounds/bg_lvl1.png", 0.1);

        player = new Player(tileMap);
        player.setPosition(80, 100);
        player.setScore(0);

        stage = 0;
        drawDoors();

        hud = new HUD(player);

        bgMusic = new AudioPlayer("/Music/yoshi_song.mp3");
        bgMusic.play();

    }

    private void populateThings0() {

        Slugger s;
        Fly fly;
        Slime slime;

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(160, 180));

        for (Point point : sluggerPoints) {
            s = new Slugger(tileMap);
            s.setPosition(point.x, point.y);
            slugs.add(s);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(180, 100));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(220, 100));

        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
        //coins
        goldCoins = new ArrayList<Collectible>();
        GoldCoin c;
        Point[] coinPoints = new Point[]{
                new Point(260, 100),
                new Point(280, 100),
                new Point(300, 100),
                new Point(320, 100),
                new Point(340, 100),
                new Point(400, 80),
                new Point(420, 80),
                new Point(440, 80),
                new Point(620, 140),
                new Point(670, 140),
                new Point(720, 140),
                new Point(770, 140),
                new Point(820, 140),
                new Point(1005, 140),
                new Point(1050, 140),
                new Point(1125, 170),
                new Point(1305, 110),
                new Point(1365, 80),
                new Point(1580, 80),
                new Point(1600, 80),
                new Point(1620, 80),
                new Point(1725, 110),
                new Point(1845 , 140),
                new Point(2100 , 200),
                new Point(2075, 140),
                new Point(2125, 140),
                new Point(2050, 80),
                new Point(2100, 80),
                new Point(2150, 80),
                new Point(2325, 80),
                new Point(2375, 80),
                new Point(2275, 80),
                new Point(2420, 200),
                new Point(2440, 200),
                new Point(2460, 200),
                new Point(2480, 200),
                new Point(2655, 170),
                new Point(2625, 170),
                new Point(2685, 170),
                new Point(2855, 170),
                new Point(2885, 170),
                new Point(2825, 170),
                new Point(2985, 140),
                new Point(3045, 110),
                new Point(3345, 110),
                new Point(3825, 110),
                new Point(3855, 110),
                new Point(3975, 140),
                new Point(4125, 200),
                new Point(4320, 80),
                new Point(4340, 80),
                new Point(4360, 80),
                new Point(4380, 80),
                new Point(4915, 200),
                new Point(5640, 170),
                new Point(5730, 140),
                new Point(5820, 110)
        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void populateThings1() {
        Slugger s;
        Fly fly;
        Slime slime;

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(260, 180));
        for (Point point : sluggerPoints) {
            s = new Slugger(tileMap);
            s.setPosition(point.x, point.y);
            slugs.add(s);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(280, 100));
        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(320, 100));
        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
    }
    private void populateThings2() {
        Slugger s;
        Fly fly;
        Slime slime;

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(1580, 100));
        for (Point point : sluggerPoints) {
            s = new Slugger(tileMap);
            s.setPosition(point.x, point.y);
            slugs.add(s);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(1600, 100));
        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(1600, 100));
        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
    }
    private void populateThings3() {

        Slugger s;
        Fly fly;
        Slime slime;

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(520, 180));

        for (Point point : sluggerPoints) {
            s = new Slugger(tileMap);
            s.setPosition(point.x, point.y);
            slugs.add(s);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(1320, 100));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(1320, 100));

        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
        //coins
        goldCoins = new ArrayList<Collectible>();
        GoldCoin c;
        Point[] coinPoints = new Point[]{
                new Point(3340, 100),
                new Point(3360, 100),
                new Point(3380, 100)
        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void drawDoors() {

        //key
        yKey = new YellowKey(tileMap);
        yKey.setPosition(1780, 35);
        //doors
        openDoors = new ArrayList<OpenDoor>();
        closedDoors = new ArrayList<ClosedDoor>();
        ClosedDoor d;
        Point[] doorPoints = new Point[]{
                new Point(5910, 135)
        };
        for (Point point : doorPoints) {
            d = new ClosedDoor(tileMap);
            d.setPosition(point.getX(), point.getY());
            closedDoors.add(d);
        }
    }

    public void draw(Graphics2D g) {

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
        drawThings(g, goldCoins);
    }

    private void drawEnemies(Graphics2D g, ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    private void drawThings(Graphics2D g, ArrayList<Collectible> things) {
        for (Collectible c : things) {
            c.draw(g);
        }
    }

    private void updateEnemies(ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            e.update();
        }
    }

    public void update() {

        player.update();

        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );
        //trigger 0
        if (player.gety() > 50 && stage == 0) {
            populateThings0();
            stage++;
        }
        //trigger 1
       if (player.getx() > 300 && stage == 1) {
           populateThings1();
           stage++;
        }
       //trigger 2
        if (player.getx() > 1500 && stage == 2) {
            populateThings2();
            stage++;
        }

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

        //update flies
        for (int i = 0; i < flys.size(); i++) {
            Enemy e = flys.get(i);
            e.update();
            if (e.getHealth() == 0) {
                flys.remove(i);
                i--;
            }
        }
        //update slimes
        for (int i = 0; i < slimes.size(); i++) {
            Enemy e = slimes.get(i);
            e.update();
            if (e.getHealth() == 0) {
                slimes.remove(i);
                i--;
            }
        }
        //update slugs
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
        //update shells
        for (int i = 0; i < shells.size(); i++) {
            Enemy e = shells.get(i);
            if (e.getHealth() == 0) {
                shells.remove(i);
                i--;
            }
        }
        //update goldCoins
        for (int i = 0; i < goldCoins.size(); i++) {
            goldCoins.get(i).update();
            if (goldCoins.get(i).isDead()) {
                goldCoins.remove(i);
            }
        }
        //update doors
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
        //update level
        if (player.getKey() && player.getx() == openDoors.get(0).getx() && (player.gety() <= openDoors.get(0).gety() + 5) && (player.gety() >= openDoors.get(0).gety() - 5)) {
            bgMusic.stop();
            gsm.setState(GameStateManager.LEVEL2STATE);
        }
        //update player death
        if (player.checkDead(player)) {
            bgMusic.stop();
            player.setScore(0);
            player.setCoins(0);
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
    }

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

}
