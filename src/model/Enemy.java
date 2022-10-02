package model;

public class Enemy {

    private int type;
    private int score;
    private int state;
    private String name;

    private Coordinates location;

    public Enemy(int type) {
        this.type = type;
        this.name = randName();
        this.score = EnemyType.INITIAL_SCORES_FOR_ENEMIES[type];
        this.state = EnemyType.STATE_ALIVE;

        this.location = new Coordinates();
    }

    public Enemy(int type, String name) {
        this.type = type;
        this.name = name;
        this.score = EnemyType.INITIAL_SCORES_FOR_ENEMIES[type];
        this.state = EnemyType.STATE_ALIVE;

        this.location = new Coordinates();
    }

    /**
     * Increases the score of the enemy in the ammont of points it steals from a
     * player.
     */
    public void increaseScore() {
        score += pointsThatSteals(type);
    }

    /**
     * Sets the enemy state to dead and returns its score.
     * 
     * @return current score.
     */
    public int killAndDropScore() {
        this.state = EnemyType.STATE_DEAD;
        return score;
    }

    /**
     * Changes its location to another x and y.
     * 
     * @param x value x on screen.
     * @param y value y on screen.
     */
    public void setLocationTo(int x, int y) {
        this.location.setLocationTo(x, y);
    }

    /**
     * To know if the enemy is still alive
     * 
     * @return True in case it is alive. False otherwise.
     */
    public boolean isAlive() {
        return state == EnemyType.STATE_ALIVE;
    }

    /**
     * Counts the amount of consonants in the name. Ignores The roman numbers.
     * 
     * @return amount of consonants.
     */
    public int amountOfConsonants() {
        int count = 0;
        String actualName = name.split("_")[0].toUpperCase();
        for (int i = 0; i < actualName.length(); i++) {
            if (EnemyType.CONSONANTS.contains(actualName.charAt(i) + "")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Enemigo: " + name + ", Puntos: " + score + ", Estado: "
                + (state == 0 ? "Vivo" : "Muerto")
                + ", Posición: " + "(x:" + location.getX()
                + ",y:" + location.getY() + ")\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Coordinates getLocation() {
        return location;
    }

    /**
     * Returns the score that a type of enemy steals from player.
     * 
     * @param enemyType type of the enemy
     * @return score to be stolen from player.
     */
    public static int pointsThatSteals(int enemyType) {
        return (int) (EnemyType.INITIAL_SCORES_FOR_ENEMIES[enemyType] / 3);
    }

    /**
     * Generates a random enemy
     * 
     * @return enemy generated
     */
    public static Enemy randEnemy() {
        return new Enemy((int) (Math.random() * 4));
    }

    /**
     * Returns a random name of enemy.
     * 
     * @return name.
     */
    public static String randName() {
        return EnemyType.ENEMY_NAMES[(int) (Math.random() * EnemyType.ENEMY_NAMES.length)];
    }
}
