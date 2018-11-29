package Entity;

import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends Enemy {

    private int coinScore = 100;
    protected boolean dead;
    private BufferedImage[] sprites;

    public Coin(TileMap tm){
        super(tm);

        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 70;
        height = 70;
        cwidth = 20;
        cheight = 20;

        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Items/coinGold.png"
                    )
            );
            sprites = new BufferedImage[1];
            for(int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(
                        i * width,
                        0,
                        width,
                        height
                );
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);
    }




    public int getCoinScore() {
        return coinScore;
    }

    public void setCoinScore(int coinScore) {
        this.coinScore = coinScore;
    }



    public void pickUp(){

        Player.score += getCoinScore();

    }

    private void getNextPosition() {

        //falling
        if(falling) {
            dy += fallSpeed;
        }

    }

    public boolean isDead() { return dead; }

    public void update(){
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        animation.update();
    }

    public void draw(Graphics2D g) {

        //if(notOnScreen()) return;

        setMapPosition();
        super.draw(g);

    }
}
