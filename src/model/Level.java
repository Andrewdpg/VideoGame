package model;

public class Level {

    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD = 2;

    private int number;
    private int nextLevelScore;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private int difficulty;

    public Level(int number) {
        this.number = number;
    }

    public void addEnemy(Enemy enemy) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null) {
                enemies[i] = enemy;
                i = enemies.length;
            }
        }
    }

    public void addTreasure(Treasure treasure) {
        for (int i = 0; i < treasures.length; i++) {
            if (treasures[i] == null) {
                treasures[i] = treasure;
                i = treasures.length;
            }
        }
    }

    public void calculateDifficulty() {
        int totalScoreEnemies = totalEnemiesScore();
        int totalScoreTreasures = totalTreasuresScore();

        if (totalScoreTreasures > totalScoreEnemies) {
            difficulty = DIFFICULTY_EASY;
        }
        if (totalScoreTreasures == totalScoreEnemies) {
            difficulty = DIFFICULTY_MEDIUM;
        }
        if (totalScoreTreasures < totalScoreEnemies) {
            difficulty = DIFFICULTY_HARD;
        }
    }

    public int totalEnemiesScore() {
        int totalScoreEnemies = 0;
        for (Enemy enemy : enemies) {
            if (enemy != null) {
                totalScoreEnemies += enemy.getScore();
            }
        }
        return totalScoreEnemies;
    }

    public int totalTreasuresScore() {
        int totalScoreTreasures = 0;
        for (Treasure treasure : treasures) {
            if (treasure != null) {
                totalScoreTreasures += treasure.getScore();
            }
        }
        return totalScoreTreasures;
    }

    public int totalLevelScore() {
        return totalEnemiesScore() + totalTreasuresScore();
    }

    public String getInfo() {
        String msg = "\nNivel " + (number + 1) + "\n";
        msg += "Puntaje necesario para el siguiente nivel:" + nextLevelScore + "\n\n";
        msg += "Enemigos: \n\n";
        if (enemies != null) {
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    msg += enemies[i].getInfo();
                }
            }
        }
        msg += "\nTesoros: \n\n";
        if (treasures != null) {
            for (int i = 0; i < treasures.length; i++) {
                if (treasures[i] != null) {
                    msg += treasures[i].getInfo();
                }
            }
        }
        return msg;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int id) {
        this.number = id;
    }

    public int getNextLevelScore() {
        return nextLevelScore;
    }

    public void setNextLevelScore(int nextLevelScore) {
        this.nextLevelScore = nextLevelScore;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public void setTreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }

}
