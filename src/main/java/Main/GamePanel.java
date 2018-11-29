package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;


    Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D graphics;
    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        gsm.keyPressed(keyEvent.getKeyCode());

    }

    public void keyReleased(KeyEvent keyEvent) {
        gsm.keyReleased(keyEvent.getKeyCode());

    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
        running = true;
        gsm = new GameStateManager();
    }

    public void run() {

        long start;
        long elapsed;
        long wait;

        init();

        while (running) {
            update();
            draw();
            drawToScreen();

            start = System.nanoTime();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        gsm.update();
    }

    public void draw() {
        gsm.draw(graphics);
    }

    public void drawToScreen() {
        Graphics graphics1 = getGraphics();
        graphics1.drawImage(image, 0, 0,WIDTH * SCALE,HEIGHT * SCALE , null);
        graphics1.dispose();
    }
}
