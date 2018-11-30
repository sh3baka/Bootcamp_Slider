package GameState;

import Entity.Characters.Enemy;
import Entity.Characters.Player;
import Entity.Characters.Slugger;
import Entity.Collectible.Coin;
import Entity.Effects.Explosion;
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
    private ArrayList<Enemy> enemies;
    private ArrayList<Coin> coins;
    private ArrayList<Explosion> explosions;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl.png");
        tileMap.loadMap("/TestMap.csv");
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.01);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(40, 100);
        player.setScore(0);

        populateEnemies();
        populateCoins();

        hud = new HUD(player);
    }

    private void populateEnemies() {

        enemies = new ArrayList<Enemy>();

        Slugger s;

        Point[] points = new Point[]{
                new Point(160, 200),
                new Point(260, 200),
                new Point(460, 200)
        };
        for (int i = 0; i < points.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);

            explosions = new ArrayList<Explosion>();
        }
    }

    private void populateCoins() {

        coins = new ArrayList<Coin>();

        Coin c;
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
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if (e.isDead()) {
                enemies.remove(i);
                i--;
                explosions.add(
                        new Explosion(e.getx(), e.gety()));
            }
        }

        //update explosions
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
        }

        //update coins
        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).update();
            if (coins.get(i).isDead()) {
                coins.remove(i);
            }
        }

        if(player.isDead()) {
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
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        //draw explosions
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition(
                    (int) tileMap.getx(), (int) tileMap.gety()
            );
            explosions.get(i).draw(g);
        }

        //draw coins
        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).draw(g);
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
