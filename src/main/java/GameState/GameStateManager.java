package GameState;

import java.awt.*;

public class GameStateManager {

    public static final int MENUSTATE = 0;
    public static final int HELP1STATE = 1;
    public static final int HELP2STATE = 2;
    public static final int HELP3STATE = 3;
    public static final int LEVEL1STATE = 4;
    public static final int LEVEL2STATE = 5;
    public static final int LEVEL3STATE = 6;
    public static final int NUMGAMESTATE = 7;
    private GameState[] gameStates;
    private int currentState;

    public GameStateManager() {

        gameStates = new GameState[NUMGAMESTATE];

        currentState = MENUSTATE;
        loadState(currentState);


    }

    private void loadState(int state) {
        if (state == MENUSTATE)
            gameStates[state] = new MenuState(this);
        if (state == HELP1STATE)
            gameStates[state] = new Help1State(this);
        if (state == HELP2STATE)
            gameStates[state] = new Help2State(this);
        if (state == HELP3STATE)
            gameStates[state] = new Help3State(this);
        if (state == LEVEL1STATE)
            gameStates[state] = new Level1State(this);
        if (state == LEVEL2STATE)
            gameStates[state] = new Level2State(this);
        if (state == LEVEL3STATE)
            gameStates[state] = new Level3State(this);

    }

    private void unloadState(int state) {

        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
        gameStates[currentState].init();
    }

    public void update() {
        try {
            gameStates[currentState].update();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g) {
        try {
            gameStates[currentState].draw(g);
        } catch (Exception e) {
        }
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }

}
