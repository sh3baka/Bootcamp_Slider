package GameState;

import Entity.Entety.Enemies.Slugger;
import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;
import Entity.HUD;
import Entity.Enemy;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Entity.Enemy.*;

public class Level1State extends GameState {

    private TileMap tileMap;
    Player player;
    private Background bg;

    private HUD hud;
    private ArrayList<Enemy> enemies;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1_v4.png");
        tileMap.loadMap("/TestMap.csv");
        tileMap.setPosition(0,0);
        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        player = new Player(tileMap);
        player.setPosition(250,150);

        enemies = new ArrayList<Enemy>();

        Slugger s;
        s = new Slugger(tileMap);
        s.setPosition(100, 100);
        enemies.add(s);

        hud = new HUD(player);
    }
    public void update() {
        player.update();

        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
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
    }
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_SPACE) player.setJumping(true);
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        if(k == KeyEvent.VK_SPACE) player.setJumping(false);
    }

}
