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
    private int[][] map;
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

            BufferedImage subimage;
            for (int col = 0; col < numTilesAcross; col++) {
                subimage = tileset.getSubimage(
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize
                );
                tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                subimage = tileset.getSubimage(
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize
                );
                tiles[1][col] = new Tile(subimage, Tile.BLOCKED); //resources
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String s) { //resources

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getTileSize() {return tileSize; }
    public int getx() { return (int)x; }
    public int gety() { return  (int)y; }
    public int getWidth() {return width; }
    public int getHeight() { return height; }

    public int getType(int row, int col) {
        int rc = map[row][col]; //resources
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType();

    }

    public void setPosition(double x, double y) {

        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int)-this.x / tileSize;
        rowOffset = (int)-this.y / tileSize;

    }

    private void fixBounds() {
        if(x < xmin) x = xmin;
        if(x < ymin) y = ymin;
        if(x > xmax) x = xmax;
        if(x > ymax) y = ymax;
    }

    public  void draw(Graphics2D g) {

        for(
                int row = rowOffset;
                row < rowOffset + numRowsToDraw;
                row++) {

            if(row >= numRows) break;

            for (
                    int col = colOffset;
                    col < colOffset + numNumColsToDraw;
                    col++) {

                if(col >= numCols) break;

                if(map[row][col] == 0) continue; //res

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(
                        tiles[r][c].getImage(),
                        (int)x + col * tileSize,
                        (int)y + row * tileSize,
                        null
                );



            }


        }

    }

}
