package GameState;

import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Background;
import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tiles_lvl1_v3.png");
        tileMap.loadMap("/testMap.map");
        bg = new Background("/bg.png",0.1);


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
