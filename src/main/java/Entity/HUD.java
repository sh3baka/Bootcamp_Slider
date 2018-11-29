package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {

    private Player player;

    private BufferedImage image;
    private Font font;

    public GamePanel gamePanel = new GamePanel();

    public HUD(Player p) {
        player = p;
        try{
            image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/HUD/hud_heartFull.png"

                    )
            );
            font = new Font("Arial", Font.PLAIN,14);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g) {

        g.drawImage(image, 0, 1, null);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 30, 25);
        g.drawString(Player.getScore(), 50, 50);
    }

}
