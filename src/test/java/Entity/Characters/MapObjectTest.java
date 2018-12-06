package Entity.Characters;

import TileMap.TileMap;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapObjectTest {

    TileMap tileMap = new TileMap(30);

    @Test
    public void intersects() {
        Player player = new Player(tileMap);
        Slugger slugger = new Slugger(tileMap);

        assertTrue(player.intersects(slugger));
    }

//    @Test
//    public void calculateCorners() {
//    }
//
//    @Test
//    public void checkTileMapCollision() {
//    }

    @Test
    public void notOnScreen() {
        Player player = new Player(tileMap);
        player.setPosition(500,500);

        assertTrue(player.notOnScreen());
    }

//    @Test
//    public void draw() {
//    }
}