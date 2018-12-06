package GameState;

import TileMap.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Help2State extends GameState {

    private Background bg;
    private BufferedImage card;

    private Font font;

    public Help2State(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/sample.png", 1);
            bg.setVector(0, 0);

            card = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Backgrounds/helpMenu2.png"
                    )
            );

            font = new Font("Arial", Font.PLAIN, 12);

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
        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString("Collect", 12, 90);
        g.drawString("to get SCORE and COINS", 80, 90);

        g.drawString("Collect", 12, 135);
        g.drawString("to open", 80, 135);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);;
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER || k == KeyEvent.VK_RIGHT) {
            gsm.setState(GameStateManager.HELP3STATE);;
        }
        if (k == KeyEvent.VK_LEFT) {
            gsm.setState(GameStateManager.HELP1STATE);;
        }
    }

    public void keyReleased(int k) {
    }

}