package model;

public class Player {

    public static final int INITIAL_SCORE = 10;
    public static final int INITIAL_LIFES = 5;

    private String nickname;
    private String name;
    private int score;
    private int lifes;
    private int level;
    private int currentLevel;
    private Coordinates location;

    public Player(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
        this.score = INITIAL_SCORE;
        this.lifes = INITIAL_LIFES;
        this.level = 0;
        this.currentLevel = 0;
        this.location = new Coordinates(10,10);
    }

    /**
     * Adds to score an specified amount of points.
     * 
     * @param points points to add.
     */
    public void addToScore(int points) {
        score += points;
        evaluateLevel();
    }

    /**
     * Changes the score for another given and evaluates is level again.
     * 
     * @param score score to set
     */
    public void changeScore(int score) {
        this.score = score;
        evaluateLevel();
    }

    /**
     * Calculates the score needed to level up.
     * 
     * @return needed score.
     */
    public int neededScore() {
        return Game.SCORES_FOR_LEVEL[level] - score;
    }

    /**
     * Substract a life and an amont of score based on theenemy who killed it.
     * 
     * @param enemyType enemy who killed the player.
     */
    public void gotKilledBy(int enemyType) {
        lifes--;
        removeFromScore(Enemy.pointsThatSteals(enemyType));
    }

    /**
     * Removes an specified amount of points from score.
     * 
     * @param points points to substract.
     */
    public void removeFromScore(int points) {
        score = score - points;
        evaluateLevel();
    }

    /**
     * Evaluates the level of the player based on the minimun requeriments of each
     * level.
     */
    public void evaluateLevel() {

        boolean isDone = false;
        level = 0;

        for (int i = 0; i < Game.SCORES_FOR_LEVEL.length && !isDone; i++) {
            if (score >= Game.SCORES_FOR_LEVEL[i]) {
                level++;
            } else {
                isDone = true;
            }
        }

        if (level > currentLevel) {
            currentLevel = level;
            location.setLocationTo(Coordinates.generateRandX(), Coordinates.generateRandY());
        }
    }

    /**
     * Validates if the player is still alive.
     * 
     * @return True in case it is alive. False otherwise.
     */
    public boolean stillAlive() {
        return lifes > 0;
    }

    @Override
    public String toString() {
        String msg = "\nJugador " + nickname + ":\n" +
                "Puntaje: " + score + "\n" +
                "Nivel: " + level + "\n" +
                "Siguiente nivel en: "
                + (level == Game.SCORES_FOR_LEVEL.length ? 0 : Game.SCORES_FOR_LEVEL[level] - score) + " puntos\n" +
                "Vidas restantes: " + lifes + "\n";
        return msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        this.location.setLocationTo(x, y);
    }

}