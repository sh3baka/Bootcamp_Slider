package Entity.Hud;

import Entity.Characters.Player;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {

    private Player player;

    private BufferedImage heart;
    private BufferedImage heartEmpty;
    private BufferedImage coin;
    private BufferedImage key;
    private Font font;

    public HUD(Player p) {
        player = p;
        try {
            heart = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/HUD/hud_heartFull.png"

                    )
            );
            heartEmpty = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/HUD/hud_heartEmpty.png"

                    )
            );
            coin = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/HUD/hud_coins.png"

                    )
            );
            key = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Items/keyYellow.png"

                    )
            );
            font = new Font("Arial", Font.PLAIN, 14);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {

        int offset = 0;
        int offsetMax = 0;

        for (int i = 0; i < player.getMaxHealth(); i++) {

            g.drawImage(heartEmpty, 0 + offsetMax, 0, null);
            offsetMax += 20;

        }

        for (int i = 0; i < player.getHealth(); i++) {

            g.drawImage(heart, 0 + offset, 0, null);
            offset += 20;

        }
        g.drawImage(coin, GamePanel.WIDTH - 100, 1, null);
        if (player.getKey()) {
            g.drawImage(key, GamePanel.WIDTH - 150, -15, null);
        }
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + Player.getScore(), 20, 50);
        g.drawString("X " + Player.getCoins(), 260, 30);
        // Test data
                g.drawString("X " + Player.getX(), 260, 100);
                g.drawString("Y " + Player.getY(), 260, 130);
    }
}
