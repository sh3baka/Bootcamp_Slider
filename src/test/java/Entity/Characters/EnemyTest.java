package Entity.Characters;

import TileMap.TileMap;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {
    TileMap tileMap = new TileMap(30);
    Enemy enemy = new Enemy(tileMap);

    @Test
    public void isDead() {
        enemy.dead = true;

        assertTrue(enemy.isDead());
    }

    @Test
    public void getDamage() {
        enemy.damage = 200;

        assertEquals(200, enemy.getDamage());
    }

    @Test
    public void hit() {
        enemy.health = 1;

        enemy.hit(1);

        assertEquals(0, enemy.getHealth());
        assertTrue(enemy.isDead());
    }

    @Test
    public void getHealth() {
        enemy.health = 200;

        assertEquals(200, enemy.getHealth());
    }

    @Test
    public void getWorth() {
        enemy.worth = 222;

        assertEquals(222, enemy.getWorth());
    }

    @Test
    public void update() {
    }
}