package Entity.Characters;

import TileMap.TileMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private static final double DELTA = 1e-15;


    TileMap tm = new TileMap(30);
    Player player = new Player(tm);


    @Test
    public void getX() {
        player.x = 100;

        double actual = player.getx();

        assertEquals(100, actual, DELTA);
    }

    @Test
    public void getY() {
        player.y = 200;

        double actual = player.gety();

        assertEquals(200, actual, DELTA);
    }

    @Test
    public void getScore() {
        player.setScore(2222);

        int actual = player.getScore();

        assertEquals(2222, actual);
    }

    @Test
    public void setScore() {
        player.setScore(22222);

        assertEquals(22222, player.getScore());
    }

    @Test
    public void getCoins() {
        player.setCoins(5);

        assertEquals(5, player.getCoins());
    }

    @Test
    public void getKey() {
        player.setKey(true);

        assertEquals(true, player.getKey());
    }

    @Test
    public void setKey() {
        player.setKey(true);

        assertEquals(true, player.getKey());
    }

    @Test
    public void setCoins() {
        player.setCoins(555);

        assertEquals(555, player.getCoins());
    }

    @Test
    public void setDead() {
        player.setDead();

        assertEquals(true, player.isDead());
    }

//    @Test
//    public void checkDead() {
//    }
//
//    @Test
//    public void checkAttack() {
//    }
//
//    @Test
//    public void checkKeys() {
//    }
//
//    @Test
//    public void hit() {
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void checkCollect() {
//    }
//
//    @Test
//    public void draw() {
//    }
//
//    @Test
//    public void setPosition() {
//    }
}