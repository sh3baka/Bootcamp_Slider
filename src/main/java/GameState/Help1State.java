package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Help1State extends GameState {

    private Background bg;

    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };

    private Font font;

    public Help1State(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/sample.png", 1);
            bg.setVector(0, 0);

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
        g.setColor(Color.CYAN);
        g.drawString("Controls", 240, 140);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE || k == KeyEvent.VK_LEFT) {
            gsm.setState(GameStateManager.MENUSTATE);;
        }
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER || k == KeyEvent.VK_RIGHT) {
            gsm.setState(GameStateManager.HELP2STATE);;
        }
    }

    public void keyReleased(int k) {
    }

}