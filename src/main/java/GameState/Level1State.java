package GameState;

import Entity.Enemies.Slugger;
import Entity.Coin;
import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;
import Entity.HUD;
import Entity.Enemy;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {

    private TileMap tileMap;
    Player player;
    private Background bg;

    private HUD hud;
    private ArrayList<Enemy> enemies;
    private ArrayList<Coin> coins;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1_v4.png");
        tileMap.loadMap("/TestMap.csv");
        tileMap.setPosition(0,0);
        tileMap.setTween(0.06);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(40,100);
        player.setScore(0);

        populateEnemies();
        populateCoins();

        hud = new HUD(player);
    }

    private void populateEnemies() {

        enemies = new ArrayList<Enemy>();

        Slugger s;
        Point[] points = new Point[] {
                new Point(160,200),
                new Point(260, 200),
                new Point(460, 200)
        };
        for(int i = 0; i < points.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
    }

    private void populateCoins() {

        coins = new ArrayList<Coin>();

        Coin c;
        Point[] coinPoints = new Point[] {
                new Point(150,200),
                new Point(250, 200),
                new Point(450, 200)
        };
        for(int i = 0; i < coinPoints.length; i++) {
            c = new Coin(tileMap);
            c.setPosition(coinPoints[i].x, coinPoints[i].y);
            coins.add(c);
        }
    }

    public void update() {

        player.update();

        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        //check coin collect
        player.checkCollect(coins);
        player.checkDead(player);

        //set background
        bg.setPosition(tileMap.getx(), tileMap.gety());

        //attack enemies
        player.checkAttack(enemies);

        //check player attack
        player.checkAttack(enemies);

        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if(enemies.get(i).isDead()) {
                enemies.remove(i);
                i--;
            }
        }
        //update coins
        for (int i =0; i < coins.size(); i++) {
            coins.get(i).update();
            if (coins.get(i).isDead()){
                coins.remove(i);
            }
        }

        if (player.isDead()){
            gsm.setState(GameStateManager.LEVEL1STATE);
        }

    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        tileMap.draw(g);
        player.draw(g);


        //draw hud
        hud.draw(g);

        //draw enemies
        for (int i =0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        //draw coins
        for (int i =0; i < coins.size(); i++) {
            coins.get(i).draw(g);
        }
    }
    public void keyPressed(int k) {

        //arrows
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        //wasd
        if(k == KeyEvent.VK_A) player.setLeft(true);
        if(k == KeyEvent.VK_D) player.setRight(true);
        if(k == KeyEvent.VK_W) player.setUp(true);
        if(k == KeyEvent.VK_S) player.setDown(true);
        //
        if(k == KeyEvent.VK_SPACE) player.setJumping(true);
        if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        //wasd
        if(k == KeyEvent.VK_A) player.setLeft(false);
        if(k == KeyEvent.VK_D) player.setRight(false);
        if(k == KeyEvent.VK_W) player.setUp(false);
        if(k == KeyEvent.VK_S) player.setDown(false);
        //
        if(k == KeyEvent.VK_SPACE) player.setJumping(false);
    }

}
