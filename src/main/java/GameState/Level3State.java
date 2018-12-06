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
import java.util.ArrayList;
import java.util.LinkedList;

public class Level3State extends GameState {

    Level3State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl3.png");
        tileMap.loadMap("/levelMap3.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.06);

        initEnemyArrayLists();
        populateThings0();

        bg = new Background("/Backgrounds/bg_lvl3.png", 0.1);

        player = new Player(tileMap);
        player.setPosition(190, 0);

        stage = 1;
        drawDoors();

        hud = new HUD(player);

        bgMusic = new AudioPlayer("/Music/yoshi_song.mp3");
        bgMusic.play();

    }

    private void populateThings0() {


        LinkedList<Point> spikePoints = new LinkedList<Point>();
        spikePoints.add(new Point(110, 200));

        for (Point point : spikePoints) {
            spike = new Spike(tileMap);
            spike.setPosition(point.x, point.y);
            spikes.add(spike);
        }

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(160, 180));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(180, 100));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        //coins
        goldCoins = new ArrayList<Collectible>();
        Point[] coinPoints = new Point[]{

                new Point(160, 100),
                new Point(180, 100),
                new Point(680, 100),
                new Point(700, 100),
                new Point(720, 100),
                new Point(1150, 200),
                new Point(1170, 200),
                new Point(1190, 200),
                new Point(1340, 200),
                new Point(1775, 200),
                new Point(1795, 200),
                new Point(1805, 200),
                new Point(1150, 200),
                new Point(1170, 200),
                new Point(1190, 200),
                new Point(1335, 200),
                new Point(2290, 110),
                new Point(2310, 110),
                new Point(2330, 110)


        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void populateThings1() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(661, 140));
        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(661, 65));
        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(875, 110));
        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
        //coins
        goldCoins = new ArrayList<Collectible>();
        Point[] coinPoints = new Point[]{
                new Point(240, 100),
                new Point(260, 100),
                new Point(280, 100),
                new Point(1240, 100),
                new Point(1260, 100),
                new Point(1280, 100),
                new Point(1610, 200),
                new Point(2260, 100),
                new Point(2260, 110),
                new Point(2740, 60),
                new Point(690, 140),
                new Point(710, 140),
                new Point(730, 140)

        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void populateThings2() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(1175, 80));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(1185, 40));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(1210, 110));

        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
        //coins
        goldCoins = new ArrayList<Collectible>();
        Point[] coinPoints = new Point[]{
                new Point(340, 100),
                new Point(360, 100),
                new Point(380, 100),
                new Point(3220, 200),
                new Point(3240, 200),
                new Point(3260, 200),
                new Point(980, 110),
                new Point(1000, 110),
                new Point(1020, 110),
                new Point(1770, 80),
                new Point(1790, 80),
                new Point(2055, 110),
                new Point(2115, 110)
        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void populateThings3() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(4130, 180));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(4060, 170));

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
        Point[] coinPoints = new Point[]{
                new Point(4060, 170),
                new Point(4080, 170),
                new Point(4200, 110),
                new Point(4220, 100),
                new Point(4390, 140),
                new Point(4310, 100)
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
        yKey.setPosition(3945, 65);
        //doors
        openDoors = new ArrayList<OpenDoor>();
        closedDoors = new ArrayList<ClosedDoor>();
        ClosedDoor d;
        Point[] doorPoints = new Point[]{
                new Point(5955, 195)
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
                (float) GamePanel.WIDTH / 2 - player.getx(),
                (float) GamePanel.HEIGHT / 2 - player.gety()
        );

        //trigger 1
        if (player.getx() > 350 && stage == 1) {
            populateThings1();
            stage++;
        }
        //trigger 2
        if (player.getx() > 750 && stage == 2) {
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

        //attack slimes
        player.checkAttack(slimes);

        //attack spikes
        player.checkSpikes(spikes);
        //update flies
        updateFlies(flys);
        //update slimes
        updateFlies(slimes);
        //attack flies
        player.checkAttack(flys);

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

        // death for player if out of bounds except top of the screen
        if (player.gety() > 300 || player.getx() < 0 || player.getx() > 200*30) {
            player.isDead();
        }

        //update player death
        if (player.isDead()) {
            bgMusic.stop();
            player.setScore(0);
            player.setCoins(0);
            gsm.setState(GameStateManager.LEVEL3STATE);
        }
    }
}
