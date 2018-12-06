package GameState;

import TileMap.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Help3State extends GameState {

    private Background bg;
    private BufferedImage card;

    private Font font;

    Help3State(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/sample.png", 1);
            bg.setVector(0, 0);

            card = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Backgrounds/helpMenu3.png"
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

        g.drawString("You can and should KILL or AVOID ", 30, 100);
        g.drawString("Definitely AVOID SPIKES and GAPS", 30, 145);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER || k == KeyEvent.VK_RIGHT) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (k == KeyEvent.VK_LEFT) {
            gsm.setState(GameStateManager.HELP2STATE);
        }
    }

    public void keyReleased(int k) {
    }

}