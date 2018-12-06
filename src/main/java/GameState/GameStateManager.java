package GameState;

import java.awt.*;

public class GameStateManager {

    static final int MENUSTATE = 0;
    static final int HELP0STATE = 1;
    static final int HELP1STATE = 2;
    static final int HELP2STATE = 3;
    static final int HELP3STATE = 4;
    static final int LEVEL1STATE = 5;
    static final int LEVEL2STATE = 6;
    static final int LEVEL3STATE = 7;
    private static final int NUMGAMESTATE = 8;
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
        if (state == HELP0STATE)
            gameStates[state] = new Help0State(this);
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
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        try {
            gameStates[currentState].draw(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }

}
