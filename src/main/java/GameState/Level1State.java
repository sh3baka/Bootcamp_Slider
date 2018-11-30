package GameState;

import Audio.AudioPlayer;
import Entity.Characters.Enemy;
import Entity.Characters.Player;
import Entity.Characters.Shell;
import Entity.Characters.Slugger;
import Entity.Collectible.GoldCollectible;
import Entity.Effects.Door;
import Entity.Hud.HUD;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {

    Player player;
    private TileMap tileMap;
    private Background bg;

    private HUD hud;
    private ArrayList<Enemy> slugs;
    private ArrayList<Enemy> shells;
    //private ArrayList<Door> doors;

    private Door door;

    private AudioPlayer bgMusic;

    private ArrayList<GoldCollectible> goldCoins;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1.png");
        tileMap.loadMap("/TestMap.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.07);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(40, 100);
        player.setScore(0);

        populateEnemies();
        populateCoins();

        //buildDoors();

        hud = new HUD(player);

        bgMusic = new AudioPlayer("/Music/Fantasy_Game_Backgroundd.mp3");
        bgMusic.play();

        door = new Door(tileMap);

    }

    private void populateEnemies() {

        slugs = new ArrayList<Enemy>();
        shells = new ArrayList<Enemy>();

        Slugger s;

        Point[] points = new Point[]{
                new Point(160, 200),
                new Point(260, 200),
                new Point(460, 200)
        };
        for (int i = 0; i < points.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            slugs.add(s);
        }
    }

    private void populateCoins() {

        goldCoins = new ArrayList<GoldCollectible>();

        GoldCollectible c;
        Point[] coinPoints = new Point[]{
                new Point(140, 100),
                new Point(160, 100),
                new Point(180, 100),
                new Point(200, 100),
                new Point(210, 100),
                new Point(230, 100),
                new Point(250, 100),
                new Point(270, 100),
                new Point(290, 100),
                new Point(310, 100),
                new Point(330, 100),
                new Point(350, 100)
        };
        for (int i = 0; i < coinPoints.length; i++) {
            c = new GoldCollectible(tileMap);
            c.setPosition(coinPoints[i].x, coinPoints[i].y);
            goldCoins.add(c);
        }
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        tileMap.draw(g);
        player.draw(g);


        //draw hud
        hud.draw(g);

        //draw enemies
        //slugs
        for (int i = 0; i < slugs.size(); i++) {
            slugs.get(i).draw(g);
        }
        //shells
        for (int i = 0; i < shells.size(); i++) {
            shells.get(i).draw(g);
        }
        //draw goldCoins
        for (int i = 0; i < goldCoins.size(); i++) {
            goldCoins.get(i).draw(g);
        }

        door.draw(g);
    }

    public void update() {

        //System.out.println("X = " + doors.size());

        player.update();

        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        //check coin collect
        player.checkCollect(goldCoins);
        player.checkDead(player);

        //set background
        bg.setPosition(tileMap.getx(), tileMap.gety());

        //attack slugs
        player.checkAttack(slugs);

        //attack shells
        player.checkAttack(shells);

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

        if (player.isDead()) {
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