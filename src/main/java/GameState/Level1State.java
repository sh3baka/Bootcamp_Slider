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

public class Level1State extends GameState {


    Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        bgMusic = new AudioPlayer("/Music/level1.mp3");
        init();

    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1.png");
        tileMap.loadMap("/levelMap1.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.06);

        initEnemyArrayLists();
        populateThings0();

        bg = new Background("/Backgrounds/bg_lvl1.png", 0.1);

        player = new Player(tileMap);
        player.setPosition(80, 100);
        player.setScore(0);

        stage = 1;
        drawDoors();

        hud = new HUD(player);


        gameOverMusic = new AudioPlayer("/Music/gameOver.mp3");
        bgMusic.play();

    }

    private void populateThings0() {

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(300, 140));
        addSluggersToList(sluggerPoints);

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(180, 100));
        addFlysToList(flyPoints);

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(220, 100));
        addSlimesToList(slimePoints);

        goldCoins = new ArrayList<Collectible>();
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
                new Point(1845, 140),
                new Point(2100, 200),
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


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(1580, 80));
        addSluggersToList(sluggerPoints);

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(1640, 80));
        addFlysToList(flyPoints);

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(1600, 200));
        addSlimesToList(slimePoints);
    }

    private void populateThings2() {

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(2350, 110));
        addSluggersToList(sluggerPoints);

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(2280, 90));
        addFlysToList(flyPoints);

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(2290, 110));
        addSlimesToList(slimePoints);

    }

    private void populateThings3() {


        LinkedList<Point> spikePoints = new LinkedList<Point>();
        spikePoints.add(new Point(2800, 168));
        addSpikesToList(spikePoints);

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(2800, 170));
        addSluggersToList(sluggerPoints);

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(2820, 90));
        addFlysToList(flyPoints);

    }

    private void populateThings4() {


        LinkedList<Point> spikePoints = new LinkedList<Point>();
        spikePoints.add(new Point(4575, 168));
        addSpikesToList(spikePoints);

        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(4600, 50));
        addSluggersToList(sluggerPoints);

        LinkedList<Point> flyPoints = new LinkedList<Point>();
        flyPoints.add(new Point(4660, 100));
        addFlysToList(flyPoints);

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(4740, 200));
        addSlimesToList(slimePoints);

    }

    private void populateThings5() {


        LinkedList<Point> sluggerPoints = new LinkedList<Point>();
        sluggerPoints.add(new Point(5100, 50));
        addSluggersToList(sluggerPoints);

        LinkedList<Point> slimePoints = new LinkedList<Point>();
        slimePoints.add(new Point(5148, 800));
        addSlimesToList(slimePoints);

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
    drawPrettyMap(g);
    }

    public void update() {

        player.update();

        tileMap.setPosition(
                (float) GamePanel.WIDTH / 2 - player.getx(),
                (float) GamePanel.HEIGHT / 2 - player.gety()
        );

        //trigger 1
        if (player.getx() > 1340 && stage == 1) {
            populateThings1();
            stage++;
        }
        //trigger 2
        if (player.getx() > 200 && stage == 2) {
            populateThings2();
            stage++;
        }
        //trigger 3
        if (player.getx() > 2550 && stage == 3) {
            populateThings3();
            stage++;
        }

        //trigger 4
        if (player.getx() > 4240 && stage == 4) {
            populateThings4();
            stage++;
        }
        //trigger 5
        if (player.getx() > 4600 && stage == 5) {
            populateThings5();
            stage++;
        }

        updateCollisions();

        //
        if (player.getKey() && player.getx() == openDoors.get(0).getx() && (player.gety() <= openDoors.get(0).gety() + 5) && (player.gety() >= openDoors.get(0).gety() - 5)) {
            bgMusic.stop();
            gsm.setState(GameStateManager.LEVEL2STATE);
            stage = 0;
        }
        //update player death
        if (player.checkDead(player)) {
            gameOverMusic.play();
            bgMusic.stop();
            player.setScore(0);
            player.setCoins(0);
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
    }
}
