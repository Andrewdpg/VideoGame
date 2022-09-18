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

    public void gotKilledBy(int enemyType){
        lifes--;
        removeFromScore(Enemy.stolenPoints(enemyType));
    }

    private void removeFromScore(int points) {
        score = score - points;
        evaluateLevel();
    }

    private void evaluateLevel() {
        if (score >= LEVEL_SCORES[level]) {
            level++;
        }
        if (level > 0) {
            if (score < LEVEL_SCORES[level - 1]) {
                level--;
            }
        }
    }

    public boolean stillAlive(){
        return lifes > 0;
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

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }
}