package GameState;

import Entity.*;
import TileMap.*;

import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tiles/grass.png");

        player = new Player(tileMap);

    }
    public void update() {

        //update player
        player.update();

    }

    public void draw(Graphics2D g) {

        //draw bg
        bg.draw(g);

        //draw tilemap
        tileMap.draw(g);

        //draw player
        player.draw(g);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}

}
