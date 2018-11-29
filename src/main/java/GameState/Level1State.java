package GameState;

import Entity.Coin;
import Entity.Enemies.Slugger;
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
        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        player = new Player(tileMap);

        enemies = new ArrayList<Enemy>();
        coins = new ArrayList<Coin>();

        Coin coin;
        coin = new Coin(tileMap);
        coin.setPosition(100, 100);
        coins.add(coin);


        Slugger s;
        s = new Slugger(tileMap);
        s.setPosition(100, 160);
        enemies.add(s);
        player.setPosition(40, 100);

        hud = new HUD(player);
    }
    public void update() {
        player.update();

        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        //check coin collect
        player.checkCollect(coins);

        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

        //update coins
        for (int i =0; i < coins.size(); i++) {
            coins.get(i).update();
            if (coins.get(i).isDead()){
                coins.remove(i);
            }
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
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_SPACE) player.setJumping(true);
        if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        if(k == KeyEvent.VK_SPACE) player.setJumping(false);
    }

}
