package GameState;

import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;

import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;
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
        player.setPosition(100,100);
    }
    public void update() {
        player.update();


    }
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        tileMap.draw(g);
        player.draw(g);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}

}
