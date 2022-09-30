package model;

public class Level {

    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD = 2;
    public static final String[] DIFFICULTIES = { "EASY", "MEDIUM", "HARD" };

    private int number;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private int difficulty;

    public Level(int number) {
        this.number = number;
        enemies = new Enemy[1];
        treasures = new Treasure[1];
    }

    public void addEnemy(Enemy enemy) {
        boolean isFound = false;

        for (int i = 0; i < enemies.length && !isFound; i++) {
            if (enemies[i] == null) {
                enemies[i] = enemy;
                isFound = true;
            }
        }

        if(!isFound){
            Enemy[] tempEnemies = new Enemy[enemies.length +1];
            for (int i = 0; i < enemies.length; i++) {
                tempEnemies[i] = enemies[i];
            }
            tempEnemies[tempEnemies.length-1] = enemy;
            enemies = tempEnemies;
        }
    }

    public void addTreasure(Treasure treasure) {

        boolean isFound = false;

        for (int i = 0; i < treasures.length && !isFound; i++) {
            if (treasures[i] == null) {
                treasures[i] = treasure;
                isFound = true;
            }
        }

        if(!isFound){
            Treasure[] tempTreasures = new Treasure[treasures.length +1];
            for (int i = 0; i < treasures.length; i++) {
                tempTreasures[i] = treasures[i];
            }
            tempTreasures[tempTreasures.length-1] = treasure;
            treasures = tempTreasures;
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

    public String difficultyAsString() {
        return DIFFICULTIES[this.difficulty];
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

    @Override
    public String toString() {
        String msg = "\nNivel " + (number + 1) + "\n";
        msg += "Puntaje necesario para el siguiente nivel:" + Game.SCORES_FOR_LEVEL[number] + "\n\n";
        msg += "Enemigos: \n\n";
        if (enemies != null) {
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    msg += enemies[i].toString();
                }
            }
        }
        msg += "\nTesoros: \n\n";
        if (treasures != null) {
            for (int i = 0; i < treasures.length; i++) {
                if (treasures[i] != null) {
                    msg += treasures[i].toString();
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
        return Game.SCORES_FOR_LEVEL[number];
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
