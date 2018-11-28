package GameState;

import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    private TileMap tileMap;
    Player player;
    private Background bg;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1_v2.png");
        tileMap.loadMap("/TestMap.csv");
        tileMap.setPosition(0,0);
        bg = new Background("/111.png", 0.1);
        player = new Player(tileMap);
        player.setPosition(200,50);
    }
    public void update() {
        player.update();


    }
    public void draw(Graphics2D g) {
        bg.draw(g);
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
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
