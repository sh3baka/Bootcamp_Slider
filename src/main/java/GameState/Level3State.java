package GameState;

import Audio.AudioPlayer;
import Entity.Characters.Enemy;
import Entity.Characters.Player;
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

public class Level3State extends GameState {

    Level3State(GameStateManager gsm) {
        this.gsm = gsm;
        bgMusic = new AudioPlayer("/Music/level3.mp3");
        gameOverMusic = new AudioPlayer("/Music/gameOver.mp3");
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl3.png");
        tileMap.loadMap("/levelMap3.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.06);

        initPointLists();
        initEnemyArrayLists();
        populateThings0();

        background = new Background("/Backgrounds/bg_lvl3.png", 0.1);

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

        sluggerPoints.add(new Point(661, 140));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(180, 100));
        flyPoints.add(new Point(661, 65));
        addFlysToList(flyPoints);

        //coins
        goldCoins = new ArrayList<Collectible>();
        Point[] coinPoints = new Point[]{
                new Point(240, 100),
                new Point(260, 100),
                new Point(280, 100),
                new Point(160, 100),
                new Point(180, 100),
                new Point(680, 100),
                new Point(700, 100),
                new Point(720, 100),
                new Point(690, 140),
                new Point(710, 140),
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
        sluggerPoints.add(new Point(661, 140));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(661, 65));
        addFlysToList(flyPoints);

        slimePoints.add(new Point(875, 110));
        addSlimesToList(slimePoints);

        goldCoins = new ArrayList<Collectible>();
        Point[] coinPoints = new Point[]{
                new Point(1240, 100),
                new Point(1260, 100),
                new Point(1280, 100),
                new Point(1335, 200),
                new Point(1610, 200),
                new Point(2260, 100),
                new Point(2260, 110),
                new Point(2290, 110),
                new Point(2310, 110),
                new Point(2330, 110),
                new Point(2740, 60)
        };
        for (Point point : coinPoints) {
            c = new GoldCoin(tileMap);
            c.setPosition(point.x, point.y);
            goldCoins.add(c);
        }
    }

    private void populateThings2() {
        sluggerPoints.add(new Point(1175, 80));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(1185, 40));
        addFlysToList(flyPoints);

        slimePoints.add(new Point(1210, 110));
        addSlimesToList(slimePoints);

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
        sluggerPoints.add(new Point(4130, 180));
        addSluggersToList(sluggerPoints);

        flyPoints.add(new Point(4060, 170));
        addFlysToList(flyPoints);

        slimePoints.add(new Point(220, 100));
        addSlimesToList(slimePoints);

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

        updateCollisions();

        if (player.getKey() && player.getx() == openDoors.get(0).getx() && (player.gety() <= openDoors.get(0).gety() + 5) && (player.gety() >= openDoors.get(0).gety() - 5)) {
            bgMusic.stop();
            gsm.setState(GameStateManager.MENUSTATE);
            stage = 0;
        }
        updateDeath();
    }
}
