package model;

public class EnemyType {

        // States of the enemies.
        public static final int STATE_ALIVE = 0;
        public static final int STATE_DEAD = 1;

        // Images of each type of enemy.
        public static final String[] ENEMY_IMAGES_FOR_TYPE = {};
        // Initial scores for enemies.
        public static final int[] INITIAL_SCORES_FOR_ENEMIES = { 2, 5, 10, 20 };

        public static final String LIST_OF_ENEMIES = "0. OGRE.\n" +
                        "1. ABSTRACT\n" +
                        "2. MAGICIAN\n" +
                        "3. BOSS\n";

        // List of possible enemy names.
        public static final String[] ENEMY_NAMES = { "Kuro", "Dr. Wily", "Alduin", "Vaas", "Dr. Neo", "Borgia", "Shao",
                        "Jack", "Ares", "Ridley", "Glados", "Mantis", "Blinky", "Nemesis", "Sephir" };

        // List of basic roman numbers for enemy names.
        public static final String[] ROMAN_NUMBERS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };

        public static final String CONSONANTS = "BCDFGHJKLMNÑPQRSTVWXYZ";
}
