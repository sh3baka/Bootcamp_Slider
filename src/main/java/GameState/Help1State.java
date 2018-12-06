package GameState;

import TileMap.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Help1State extends GameState {

    private Background bg;
    private BufferedImage card;

    private Font font;

    public Help1State(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/sample.png", 1);
            bg.setVector(0, 0);

            card = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Backgrounds/helpMenu1.png"
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
        g.drawString("Press", 12, 90);
        g.drawString("key to move RIGHT", 80, 90);

        g.drawString("Press", 12, 135);
        g.drawString("key to move LEFT", 80, 135);

        g.drawString("Press", 12, 180);
        g.drawString("key to JUMP", 120, 180);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE || k == KeyEvent.VK_LEFT) {
            gsm.setState(GameStateManager.HELP0STATE);;
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER || k == KeyEvent.VK_RIGHT) {
            gsm.setState(GameStateManager.HELP2STATE);;
        }
    }

    public void keyReleased(int k) {
    }

}