package Entity.Entity.Enemies;

import Entity.*;
import TileMap.TileMap;

public class Slugger extends Enemy {

    public Slugger (TileMap tm) {

        super(tm);

    moveSpeed = 0.3;
    maxSpeed = 0.3;

}
