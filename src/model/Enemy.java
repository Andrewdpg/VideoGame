package model;

public class Enemy {

    public static final int OGRE = 0; // 10
    public static final int ABSTRACT = 1; // 7
    public static final int MAGICIAN = 2; // 5
    public static final int BOSS = 3; // 3

    public static final int STATE_ALIVE = 0;
    public static final int STATE_DEAD = 1;

    public static final String[] IMAGES = {};
    public static final int[] INITIAL_SCORES = { 2, 5, 10, 20 };

    private int type;
    private int score;
    private int state;

    private int posX;
    private int posY;

    public Enemy(int type) {
        this.type = type;
        this.score = INITIAL_SCORES[type];
        this.state = STATE_ALIVE;

        posX = Coordinates.generateRandX();
        posY = Coordinates.generateRandY();
    }

    public void increaseScore() {
        score += pointsThatSteals(type);
    }

    public int killAndDropScore() {
        this.state = STATE_DEAD;
        return score;
    }

    public void setLocationTo(int x, int y) {
        posX = x;
        posY = y;
    }

    public String getInfo() {
        return "Enemigo tipo: " + type + ", Puntos: " + score + ", Estado: " + state + ", Posición: " + "(x:" + posX
                + ",y:" + posY + ")\n";
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

    public boolean isAlive() {
        return state == STATE_ALIVE;
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public static int pointsThatSteals(int enemyType) {
        return (int) (INITIAL_SCORES[enemyType] / 3);
    }

    public static Enemy randEnemy() {
        return new Enemy((int) (Math.random() * 3));
    }
}
