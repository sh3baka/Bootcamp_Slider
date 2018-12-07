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

        initPointLists();
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

        spikePoints.add(new Point(110, 200));
        addSpikesToList(spikePoints);

        sluggerPoints.add(new Point(160, 200));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(180, 120));
        addFlysToList(flyPoints);

        slimePoints.add(new Point(220, 170));
        addSlimesToList(slimePoints);

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

            spikePoints.add(new Point(5810, 110));
            addSpikesToList(spikePoints);

            sluggerPoints.add(new Point(370, 80));
            addSluggersToList(sluggerPoints);

            flyPoints.add(new Point(280, 80));
            addFlysToList(flyPoints);
        }


    private void populateThings2() {

        sluggerPoints.add(new Point(900, 50));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(950, 50));
        addFlysToList(flyPoints);

    }

    private void populateThings3() {

        sluggerPoints.add(new Point(1340, 200));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(1340, 120));
        addFlysToList(flyPoints);

        slimePoints.add(new Point(1620, 200));
        addSlimesToList(slimePoints);
    }

    private void populateThings4() {

        sluggerPoints.add(new Point(1980, 170));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(2000, 130));
        addFlysToList(flyPoints);

    }

    private void populateThings5() {

        sluggerPoints.add(new Point(2465, 140));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(2750, 100));
        addFlysToList(flyPoints);

    }

    private void populateThings6() {

        flyPoints.add(new Point(3210, 120));
        addFlysToList(flyPoints);

        slimePoints.add(new Point(3300, 200));
        addSlimesToList(slimePoints);
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
        drawPrettyMap(g);
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

    public void update() {

        player.update();

        tileMap.setPosition(
                (float) GamePanel.WIDTH / 2 - player.getx(),
                (float) GamePanel.HEIGHT / 2 - player.gety()
        );
        //trigger 0
        if (player.getx() > 420 && stage == 0) {
            populateThings0();
            stage++;
        }
        //trigger 1
        if (player.getx() > 500 && stage == 1) {
            populateThings1();
            stage++;
        }
        //trigger 2
        if (player.getx() > 700 && stage == 2) {
            populateThings2();
            stage++;
        }
        //trigger 3
        if (player.getx() > 1200 && stage == 3) {
            populateThings3();
            stage++;
        }

        //trigger 4
        if (player.getx() > 2100 && stage == 4) {
            populateThings4();
            stage++;
        }
        //trigger 5
        if (player.getx() > 2800 && stage == 5) {
            populateThings5();
            stage++;
        }
        updateCollisions();

        //
        if (player.getKey() && player.getx() == openDoors.get(0).getx() && (player.gety() <= openDoors.get(0).gety() + 5) && (player.gety() >= openDoors.get(0).gety() - 5)) {
            bgMusic.stop();
            gsm.setState(GameStateManager.LEVEL3STATE);
            stage = 0;
        }
        updateDeath();
    }
}
