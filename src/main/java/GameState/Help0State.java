package GameState;

import TileMap.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Help0State extends GameState {

    private Background bg;
    private BufferedImage card;

    private Font font;

    public Help0State(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/sample.png", 1);
            bg.setVector(0, 0);

            card = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Backgrounds/helpMenu1.png"
                    )
            );

            font = new Font("Arial", Font.PLAIN, 32);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void init() {
    }

    public void update() {
        bg.update();
    }

    public void draw(Graphics2D g) {
        //draw bg
        bg.draw(g);
        g.drawImage(card, 10, 10, null);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString("intro", 60, 40);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE || k == KeyEvent.VK_LEFT) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER || k == KeyEvent.VK_RIGHT) {
            gsm.setState(GameStateManager.HELP1STATE);
        }
    }

    public void keyReleased(int k) {
    }

}
