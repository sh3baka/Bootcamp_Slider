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

            bg = new Background("/mainMenuArt.png", 1);
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
        g.drawString("Press", 60, 90);
        g.drawString("key to move RIGHT", 160, 90);

        g.drawString("Press", 60, 132);
        g.drawString("key to move LEFT", 160, 132);

        g.drawString("Press", 60, 175);
        g.drawString("key to JUMP", 195, 175);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE || k == KeyEvent.VK_LEFT) {
            gsm.setState(GameStateManager.HELP0STATE);
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER || k == KeyEvent.VK_RIGHT) {
            gsm.setState(GameStateManager.HELP2STATE);
        }
    }

    public void keyReleased(int k) {
    }

}