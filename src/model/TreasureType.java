package model;

public class TreasureType {
    
    public static final int COIN = 0;
    public static final int CHEST = 1;
    public static final int HIGH_QUALITY_CHEST = 2;
    public static final int DIAMOND = 3;

    public static final int STATE_NOT_FOUND = 0;
    public static final int STATE_FOUND = 1;

    public static final String[] TREASURE_IMAGES = {};
    public static final String[] TREASURE_NAMES = { "COIN", "CHEST", "HIGH QUALITY CHEST", "DIAMOND" };
    public static final int[] SCORES_FOR_TREASURE = { 1, 3, 7, 15 };

    public static final String LIST_OF_TREASURES = "1. COIN.\n" +
    "2. CHEST\n" +
    "3. HIGH QUALITY CHEST\n" +
    "4. DIAMOND\n";
}
