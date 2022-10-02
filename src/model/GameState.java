package model;

import java.awt.*;

public class GameState {
    public static final String NAME = "YOQUESE";
    public static final int MAX_PLAYERS = 20;
    public static final int MAX_ENEMIES = 25;
    public static final int MAX_TREASURES = 50;
    public static final int MAX_LEVEL = 10;

    public static Dimension size;

    /**
     * Reads and asign the resolution of user's screen.
     */
    public static void initResolution() {
        size = Toolkit.getDefaultToolkit().getScreenSize();
    }
}
