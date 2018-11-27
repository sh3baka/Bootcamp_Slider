package TileMap;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.ImageIO;

public class TileMap {


    //position
    private double x;
    private double y;

    //bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween;

    //map
    private int[] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    //tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[] [] tiles;

    //drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numNumColsToDraw;

    public  TileMap(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numNumColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 0.07;
    }

    public void loadTiles(String s) {

        try{

            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numTilesAcross = tileset.getWidth() / tileSize; //from resources
            tiles = new Tile[2] [numTilesAcross];

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String s) {}

}
