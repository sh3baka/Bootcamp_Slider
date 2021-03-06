package GameState;

import Audio.AudioPlayer;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };
    private Font font;

    public MenuState(GameStateManager gsm) {
        init();
        menuBgMusic = new AudioPlayer("/Music/menu.mp3");
        menuBgMusic.play();
        this.gsm = gsm;

        try {
            bg = new Background("/mainMenuArt.png", 1);
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
        bg.draw(g);

        //draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.DARK_GRAY);
            }
            g.drawString(options[i], 240, 140 + i * 15);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            menuBgMusic.stop();
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if (currentChoice == 1) {
            gsm.setState(GameStateManager.HELP0STATE);
        }
        if (currentChoice == 2) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            AudioPlayer sfx = new AudioPlayer("/SFX/enemy_hit.mp3");
            sfx.play();
            select();
        }

        if (k == KeyEvent.VK_UP) {
            AudioPlayer sfx = new AudioPlayer("/SFX/enemy_hit.mp3");
            sfx.play();
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }

        if (k == KeyEvent.VK_DOWN) {
            AudioPlayer sfx = new AudioPlayer("/SFX/enemy_hit.mp3");
            sfx.play();
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }

        if (k == KeyEvent.VK_ESCAPE) {
            AudioPlayer sfx = new AudioPlayer("/SFX/enemy_hit.mp3");
            sfx.play();
            System.exit(0);
        }
    }

    public void keyReleased(int k) {
    }
}
