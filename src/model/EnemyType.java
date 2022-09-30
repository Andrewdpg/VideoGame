package model;

public class EnemyType {

    public static final int OGRE = 0;
    public static final int ABSTRACT = 1;
    public static final int MAGICIAN = 2;
    public static final int BOSS = 3;

    public static final int STATE_ALIVE = 0;
    public static final int STATE_DEAD = 1;

    public static final String[] ENEMY_IMAGES = {};
    public static final String[] ENEMY_NAMES = { "OGRE", "ABSTRACT", "MAGICIAN", "BOSS" };
    public static final int[] INITIAL_SCORES_FOR_ENEMIES = { 2, 5, 10, 20 };

    public static final String LIST_OF_ENEMIES = "0. OGRE.\n" +
            "1. ABSTRACT\n" +
            "2. MAGICIAN\n" +
            "3. BOSS\n";
}
