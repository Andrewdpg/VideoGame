package model;

public class Enemy {

    private int type;
    private int score;
    private int state;

    private int posX;
    private int posY;

    public Enemy(int type) {
        this.type = type;
        this.score = EnemyType.INITIAL_SCORES_FOR_ENEMIES[type];
        this.state = EnemyType.STATE_ALIVE;

        posX = Coordinates.generateRandX();
        posY = Coordinates.generateRandY();
    }

    public void increaseScore() {
        score += pointsThatSteals(type);
    }

    public int killAndDropScore() {
        this.state = EnemyType.STATE_DEAD;
        return score;
    }

    public void setLocationTo(int x, int y) {
        posX = x;
        posY = y;
    }

    @Override
    public String toString() {
        return "Enemigo: " + EnemyType.ENEMY_NAMES[type] + ", Puntos: " + score + ", Estado: " + (state == 0 ? "Vivo" : "Muerto")
                + ", Posición: " + "(x:" + posX
                + ",y:" + posY + ")\n";
    }

    public String getName() {
        return EnemyType.ENEMY_NAMES[type];
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
        return state == EnemyType.STATE_ALIVE;
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public static int pointsThatSteals(int enemyType) {
        return (int) (EnemyType.INITIAL_SCORES_FOR_ENEMIES[enemyType] / 3);
    }

    public static Enemy randEnemy() {
        return new Enemy((int) (Math.random() * 4));
    }
}
