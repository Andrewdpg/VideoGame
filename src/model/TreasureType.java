package model;

public class TreasureType {

    // States of treasures.
    public static final int STATE_NOT_FOUND = 0;
    public static final int STATE_FOUND = 1;

    // images of each type of treasure.
    public static final String[] TREASURE_IMAGES = {};
    // Names of each type of treasure.
    public static final String[] TREASURE_NAMES = { "COIN", "CHEST", "HIGH QUALITY CHEST", "DIAMOND" };
    // Scores of each type of treasure.
    public static final int[] SCORES_FOR_TREASURE = { 1, 3, 7, 15 };

    public static final String LIST_OF_TREASURES = "0. COIN.\n" +
            "1. CHEST\n" +
            "2. HIGH QUALITY CHEST\n" +
            "3. DIAMOND\n";
}
