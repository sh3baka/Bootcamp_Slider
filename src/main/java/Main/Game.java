package Main;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {

        JFrame window = new JFrame("Game");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }
}
