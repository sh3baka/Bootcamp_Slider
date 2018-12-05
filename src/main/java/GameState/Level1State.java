package GameState;

import Audio.AudioPlayer;
import Entity.Characters.Enemy;
import Entity.Characters.Player;
import Entity.Characters.Shell;
import Entity.Characters.Slugger;
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

    private YellowKey yKey;
    private ArrayList<ClosedDoor> closedDoors;
    private ArrayList<OpenDoor> openDoors;

    private AudioPlayer bgMusic;

    private ArrayList<GoldCoin> goldCoins;
    private int stage = 1;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1.png");
        tileMap.loadMap("/levelMap1.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.07);

        slugs = new ArrayList<Enemy>();
        shells = new ArrayList<Enemy>();

        bg = new Background("/Backgrounds/bg_lvl1.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(80, 100);
        player.setScore(0);


        populateItems();

        hud = new HUD(player);

        bgMusic = new AudioPlayer("/Music/yoshi_song.mp3");
        bgMusic.play();

    }

    private void populateEnemies() {




        Slugger s;


        LinkedList<Point> points = new LinkedList<Point>();
              points.add(new Point(160, 180));
                points.add(new Point(260, 180)) ;
               points.add(new Point(460, 180)) ;

               for(Point point : points){
                   if(player.getx() > 200 && player.getx() < 203) {
                       s = new Slugger(tileMap);
                       s.setPosition(point.x, point.y);
                       slugs.add(s);
                   }
               }
    }

    private void populateItems() {

        //key
        yKey = new YellowKey(tileMap);
        yKey.setPosition(150, 170);
        //coins
        goldCoins = new ArrayList<GoldCoin>();
        GoldCoin c;
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
            c = new GoldCoin(tileMap);
            c.setPosition(coinPoints[i].x, coinPoints[i].y);
            goldCoins.add(c);
        }
        //doors
        openDoors = new ArrayList<OpenDoor>();
        closedDoors = new ArrayList<ClosedDoor>();
        ClosedDoor d;
        Point[] doorPoints = new Point[]{
                new Point(5910, 135)
        };
        for (int i = 0; i < doorPoints.length; i++) {
            d = new ClosedDoor(tileMap);
            d.setPosition(doorPoints[i].getX(), doorPoints[i].getY());
            closedDoors.add(d);
        }
    }

    public void draw(Graphics2D g) {

        //background and map
        bg.draw(g);
        tileMap.draw(g);
        //doors
        for (int i = 0; i < closedDoors.size(); i++) {
            closedDoors.get(i).draw(g);
        }
        for (int i = 0; i < openDoors.size(); i++) {
            openDoors.get(i).draw(g);
        }
        //player
        player.draw(g);
        hud.draw(g);
        //key
        if(!yKey.isDead()) {
            yKey.draw(g);
        }
        //slugs
        drawAll(g, slugs);
        //shells
        drawAll(g, shells);
        //goldCoins
        for (int i = 0; i < goldCoins.size(); i++) {
            goldCoins.get(i).draw(g);
        }
    }

    private void drawAll(Graphics2D g, ArrayList<Enemy> slugs) {
        for (int i = 0; i < slugs.size(); i++) {
            slugs.get(i).draw(g);
        }
    }

    public void update() {

        player.update();

        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        populateEnemies();
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
            if (player.getKey()) {
                closedDoors.remove(i);
                i--;
                openDoors.add(
                        new OpenDoor(tileMap, d.getx(), d.gety()));
            }
        }
        //update level
        if ( player.getKey() && player.getx() == openDoors.get(0).getx() && (player.gety() <= openDoors.get(0).gety() + 5) && (player.gety() >= openDoors.get(0).gety() - 5)) {
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
