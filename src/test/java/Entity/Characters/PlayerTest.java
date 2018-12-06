package Entity.Characters;

import Entity.Collectible.Collectible;
import Entity.Collectible.GoldCoin;
import Entity.Collectible.YellowKey;
import TileMap.TileMap;
import org.junit.Test;



import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    private static final double DELTA = 1e-15;


    ArrayList<Enemy> enemies;

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

        assertTrue(player.getKey());
    }

    @Test
    public void setKey() {
        player.setKey(true);

        assertTrue(player.getKey());
    }

    @Test
    public void setCoins() {
        player.setCoins(555);

        assertEquals(555, player.getCoins());
    }

    @Test
    public void setDead() {
        player.setDead();

        assertTrue(player.isDead());
    }

    @Test
    public void checkDead() {
        player.setHealth(3);

        boolean actual = player.checkDead(player);

        assertFalse(actual);
    }

    @Test
    public void checkDeadShouldReturnTrue() {
        player.setHealth(0);

        boolean actual = player.checkDead(player);

        assertTrue(actual);
    }

    @Test
    public void checkAttack() {
        enemies = new ArrayList<Enemy>();
        enemies.add(new Slugger(tm));
        player.falling = true;

        player.checkAttack(enemies);


        assertEquals(1, enemies.get(0).health );
    }

    @Test
    public void checkKeys() {
        ArrayList<YellowKey> collectibles;
        collectibles = new ArrayList<YellowKey>();
        collectibles.add(new YellowKey(tm));

        player.checkKeys(collectibles.get(0));

        assertTrue(player.hasKey());
        assertTrue(collectibles.get(0).isDead());
    }

    @Test
    public void hit() {
        enemies = new ArrayList<Enemy>();
        enemies.add(new Slugger(tm));
        player.falling = false;
        player.setHealth(1);

        player.hit(1);

        assertEquals(0, player.getHealth());
        assertTrue(player.isDead());
    }


    @Test
    public void checkCollect() {
        player.setCoins(0);
        ArrayList<Collectible> goldCoins = new ArrayList<Collectible>();
        goldCoins.add(new GoldCoin(tm));

        player.checkCollect(goldCoins);

        assertEquals(1, player.coins);
    }

    @Test
    public void setPosition() {
        player.x = 0;
        player.y = 0;

        player.setPosition(100,200);

        assertEquals(100, player.getx());
        assertEquals(200, player.gety());
    }
}