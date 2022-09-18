package model;

public class Enemy {

    public static final int OGRE = 0; //10
    public static final int ABSTRACT = 1; //7
    public static final int MAGICIAN = 2; //5
    public static final int BOSS = 3; //3

    public static final int ALIVE = 0;
    public static final int DEAD = 1;

    public static final String[] IMAGES = {};
    public static final int[] INITIAL_SCORES = { 2, 5, 10, 20 };

    private int type;
    private int score;
    private int state;

    public Enemy(int type) {
        this.type = type;
        this.score = INITIAL_SCORES[type];
        this.state = ALIVE;
    }

    public void addScore() {
        score += stolenPoints(type);
    }

    public int killAndDropScore(){
        this.state = DEAD;
        return score;
    }

    public static int stolenPoints(int enemyType){
        return (int) (INITIAL_SCORES[enemyType] / 3);
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

    public boolean isAlive(){
        return state == ALIVE;
    }

}
