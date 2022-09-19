package model;

public class Player {

    public static final int INITIAL_SCORE = 10;
    public static final int INITIAL_LIFES = 5;
    public static final int[] LEVEL_SCORES = { 20, 30, 45, 70, 105, 150 };

    private String nickname;
    private String name;
    private int score;
    private int lifes;
    private int level;

    public Player(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
        this.score = INITIAL_SCORE;
        this.lifes = INITIAL_LIFES;
        this.level = 0;
    }

    public void addToScore(int points) {
        score += points;
        evaluateLevel();
    }

    public void changeScore(int score) {
        this.score = score;
        evaluateLevel();
    }

    public void gotKilledBy(int enemyType) {
        lifes--;
        removeFromScore(Enemy.stolenPoints(enemyType));
    }

    private void removeFromScore(int points) {
        score = score - points;
        evaluateLevel();
    }

    private void evaluateLevel() {

        boolean isDone = false;
        level = 0;

        for (int i = 0; i < LEVEL_SCORES.length && !isDone; i++) {
            if(score >= LEVEL_SCORES[i]){
                level++;
            }else{
                isDone = true;
            }
        }
    }

    public boolean stillAlive() {
        return lifes > 0;
    }

    public String getInfo() {
        String msg = "\nJugador " + nickname + ":\n" +
                "Puntaje: " + score + "\n" +
                "Nivel: " + level + "\n" +
                "Siguiente nivel en: " + (level == LEVEL_SCORES.length? 0:LEVEL_SCORES[level] - score) + " puntos\n" +
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
}