package GameState;

import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;
    Player player;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1_v2.png");
        tileMap.loadMap("/TestMap.csv");
        player = new Player(tileMap);
        player.setPosition(200,50);
    }
    public void update() {
        player.update();


        bg = new Background("/Backgrounds/bg1v2.png", 0.1);

    }
    public void draw(Graphics2D g) {
        //draw bg
        bg.draw(g);
        //draw tilemap
        tileMap.draw(g);
        player.draw(g);
    }
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
    }

}
