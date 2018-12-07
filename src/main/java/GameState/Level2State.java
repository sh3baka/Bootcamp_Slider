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

public class Level2State extends GameState {

    Level2State(GameStateManager gsm) {
        this.gsm = gsm;
        bgMusic = new AudioPlayer("/Music/level2.mp3");
        gameOverMusic = new AudioPlayer("/Music/gameOver.mp3");
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl2.png");
        tileMap.loadMap("/levelMap2.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.06);

        initEnemyArrayLists();
        populateThings0();

        bg = new Background("/Backgrounds/bg_lvl2.png", 0.1);

        player = new Player(tileMap);
        player.setPosition(190, 0);

        stage = 1;
        drawDoors();
        hud = new HUD(player);
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
        sluggerPoints.add(new Point(160, 200));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(180, 120));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(220, 170));

        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
        //coins
        goldCoins = new ArrayList<Collectible>();
        Point[] coinPoints = new Point[]{
                new Point(140, 100),
                new Point(160, 100),
                new Point(180, 100),
                new Point(2353, 170),
                new Point(3410, 200),
                new Point(3490, 200),
                new Point(3410, 200),
                new Point(3520, 80)

        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void populateThings1() {


        LinkedList<Point> spikePoints = new LinkedList<Point>();

        spikePoints.clear();
        spikePoints.add(new Point(5810, 110));

        for (Point point : spikePoints) {
            spike = new Spike(tileMap);
            spike.setPosition(point.x, point.y);
            spikes.add(spike);
        }

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(370, 80));
        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(280, 80));
        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

//        //coins
//        goldCoins = new ArrayList<Collectible>();
//        Point[] coinPoints = new Point[]{
//                new Point(240, 100),
//                new Point(260, 100),
//                new Point(280, 100),
//                new Point(1800, 100),
//                new Point(1820, 100),
//                new Point(1840, 100),
//                new Point(1860, 100)
//        };
//        for (Point point : coinPoints) {
//            c = new GoldCoin(tileMap);
//            c.setPosition(point.x, point.y);
//            goldCoins.add(c);
//        }
    }

    private void populateThings2() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(900, 50));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(950, 50));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

//        //coins
//        goldCoins = new ArrayList<Collectible>();
//        Point[] coinPoints = new Point[]{
//                new Point(340, 100),
//                new Point(360, 100),
//                new Point(380, 100),
//                new Point(440, 80),
//                new Point(460, 80),
//                new Point(480, 80),
//                new Point(1280, 200),
//                new Point(1300, 200),
//                new Point(1320, 200),
//                new Point(2000, 150),
//                new Point(2670, 140),
//                new Point(2690, 140),
//                new Point(2710, 140),
//                new Point(3800, 80),
//                new Point(3820, 80),
//                new Point(3840, 80),
//                new Point(4100, 200),
//                new Point(4120, 200),
//                new Point(4150, 40),
//                new Point(4160, 40),
//                new Point(4180, 40),
//                new Point(4510, 200),
//                new Point(4815, 60),
//                new Point(4900, 40)
//
//
//        };
//        for (Point point : coinPoints) {
//            c = new GoldCoin(tileMap);
//            c.setPosition(point.x, point.y);
//            goldCoins.add(c);
//        }
    }

    private void populateThings3() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(1340, 200));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(1340, 120));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(1620, 200));

        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
    }

    private void populateThings4() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(1980, 170));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(2000, 130));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

    }

    private void populateThings5() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(2465, 140));

        for (Point point : sluggerPoints) {
            slugger = new Slugger(tileMap);
            slugger.setPosition(point.x, point.y);
            slugs.add(slugger);
        }

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(2750, 100));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

    }

    private void populateThings6() {


        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(3210, 120));

        for (Point point : flyPoints) {
            fly = new Fly(tileMap);
            fly.setPosition(point.x, point.y);
            flys.add(fly);
        }

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(3300, 200));

        for (Point point : slimePoints) {
            slime = new Slime(tileMap);
            slime.setPosition(point.x, point.y);
            slimes.add(slime);
        }
    }

    private void drawDoors() {

        //key
        yKey = new YellowKey(tileMap);
        yKey.setPosition(3970, 200);
        //doors
        openDoors = new ArrayList<OpenDoor>();
        closedDoors = new ArrayList<ClosedDoor>();
        ClosedDoor d;
        Point[] doorPoints = new Point[]{
                new Point(5910, 105)
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

    public void update() {

        player.update();

        tileMap.setPosition(
                (float) GamePanel.WIDTH / 2 - player.getx(),
                (float) GamePanel.HEIGHT / 2 - player.gety()
        );
        //trigger 1
        if (player.getx() > 420 && stage == 1) {
            populateThings1();
            stage++;
        }
        //trigger 2
        if (player.getx() > 600 && stage == 2) {
            populateThings2();
            stage++;
        }
        //trigger 3
        if (player.getx() > 990 && stage == 3) {
            populateThings3();
            stage++;
        }
        //trigger 4
        if (player.getx() > 1600 && stage == 4) {
            populateThings4();
            stage++;
        }

        //trigger 5
        if (player.getx() > 2100 && stage == 5) {
            populateThings5();
            stage++;
        }
        //trigger 6
        if (player.getx() > 2800 && stage == 6) {
            populateThings6();
            stage++;
        }


        //coin collect
        player.checkCollect(goldCoins);
        //keys
        player.checkKeys(yKey);
        //set background
        bg.setPosition(tileMap.getx(), tileMap.gety());

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

        //
        if (player.getKey() && player.getx() == openDoors.get(0).getx() && (player.gety() <= openDoors.get(0).gety() + 5) && (player.gety() >= openDoors.get(0).gety() - 5)) {
            bgMusic.stop();
            gsm.setState(GameStateManager.LEVEL3STATE);
            stage = 0;
        }
        //update player death
        if (player.checkDead(player)) {
            bgMusic.stop();
            gameOverMusic.play();
            player.setScore(0);
            player.setCoins(0);
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
    }
}
