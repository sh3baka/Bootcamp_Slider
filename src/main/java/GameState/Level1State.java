package GameState;

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
        tileMap.loadMap("/TestMap.");

    }
    public void update() {}
    public void draw(Graphics2D g) {
        tileMap.draw(g);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}

}
