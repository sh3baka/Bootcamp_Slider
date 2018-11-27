package GameState;

import Main.GamePanel;
import TileMap.TileMap;

import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tiles/grass.png");
        tileMap.loadTiles("/Tiles/box.png");
        tileMap.loadMap("/TestMap2.csv");

    }
    public void update() {}
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        tileMap.draw(g);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}

}
