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

    /**
     * Adds an enemy to the current level. In case the array is full it will replace
     * the current array of enemies with another array of a bigger size.
     * 
     * @param enemy enemy to add.
     */
    public void addEnemy(Enemy enemy) {
        boolean isFound = false;
        enemy.setName(enemy.getName() + "_" + EnemyType.ROMAN_NUMBERS[number] + "_"
                + getEnemyNumber(enemy.getName()));
        for (int i = 0; i < enemies.length && !isFound; i++) {
            if (enemies[i] == null) {
                enemies[i] = enemy;
                isFound = true;
            }
        }
        if (!isFound) {
            Enemy[] tempEnemies = new Enemy[enemies.length + 1];
            for (int i = 0; i < enemies.length; i++) {
                tempEnemies[i] = enemies[i];
            }
            tempEnemies[tempEnemies.length - 1] = enemy;
            enemies = tempEnemies;
        }
    }

    /**
     * Adds an treasure to the current level. In case the array is full it will
     * replace
     * the current array of trasures with another array of a bigger size.
     * 
     * @param enemy enemy to add.
     */
    public void addTreasure(Treasure treasure) {
        boolean isFound = false;
        for (int i = 0; i < treasures.length && !isFound; i++) {
            if (treasures[i] == null) {
                treasures[i] = treasure;
                isFound = true;
            }
        }
        if (!isFound) {
            Treasure[] tempTreasures = new Treasure[treasures.length + 1];
            for (int i = 0; i < treasures.length; i++) {
                tempTreasures[i] = treasures[i];
            }
            tempTreasures[tempTreasures.length - 1] = treasure;
            treasures = tempTreasures;
        }
    }

    /**
     * Calculates the difficulty of the level based on the scores of enemies an
     * treasures.
     */
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

    /**
     * Returns the difficulty of the level, as a String.
     * 
     * @return difficulty as String.
     */
    public String difficultyAsString() {
        return DIFFICULTIES[this.difficulty];
    }

    /**
     * Calculates the total score of all the enemies on level.
     * 
     * @return score of all enemies.
     */
    public int totalEnemiesScore() {
        int totalScoreEnemies = 0;
        for (Enemy enemy : enemies) {
            if (enemy != null) {
                totalScoreEnemies += enemy.getScore();
            }
        }
        return totalScoreEnemies;
    }

    /**
     * Calculates the total score of all the treasures on level.
     * 
     * @return score of all treasures.
     */
    public int totalTreasuresScore() {
        int totalScoreTreasures = 0;
        for (Treasure treasure : treasures) {
            if (treasure != null) {
                totalScoreTreasures += treasure.getScore();
            }
        }
        return totalScoreTreasures;
    }

    /**
     * Calculates the total score in the level.
     * 
     * @return total score of level.
     */
    public int totalLevelScore() {
        return totalEnemiesScore() + totalTreasuresScore();
    }

    /**
     * Count the amount of treasures, of an specific type, in level.
     * 
     * @param type type of treasure to look for.
     * @return A response with the resulting operation. A message with the
     *         information of all of the treasures found. Also the amount of
     *         treasures found as data.
     */
    public Response countOfTreasures(int type) {
        int count = 0;
        String msg = "";
        for (int i = 0; i < treasures.length; i++) {
            if (treasures[i] != null) {
                if (treasures[i].getType() == type) {
                    count++;
                    msg += "Nivel " + (number + 1) + " - " + treasures[i].toString();
                }
            }
        }
        return new Response(false, msg, count);
    }

    /**
     * Count the amount of enemies, of an specific type, in level.
     * 
     * @param type type of enemy to look for.
     * @return A response with the resulting operation. A message with the
     *         information of all of the enemies found. Also the amount of
     *         enemies found as data.
     */
    public Response countOfEnemies(int type) {
        int count = 0;
        String msg = "";
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                if (enemies[i].getType() == type) {
                    count++;
                    msg += "Nivel " + (number + 1) + " - " + enemies[i].toString();
                }
            }
        }
        return new Response(false, msg, count);
    }

    /**
     * Looks for the enemy with the highest score. In case there is more than only
     * one with thesame score, it will take the first one in the array.
     * 
     * @return A response with the resulting operation. A message with the enemy's
     *         information. Also, its score as data.
     */
    public Response higherScoreEnemy() {
        int higher = 0;
        Enemy foundEnemy = null;
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                if (enemies[i].getScore() > higher) {
                    foundEnemy = enemies[i];
                    higher = foundEnemy.getScore();
                }
            }
        }
        return new Response(foundEnemy == null,
                foundEnemy == null ? "" : "Nivel " + (number + 1) + " - " + foundEnemy.toString(), higher);
    }

    /**
     * Counts the total of consonants in the names of all the enemies. Ignores The
     * roman numbers.
     * 
     * @return amount of consonants.
     */
    public Response consonantsInEnemies() {
        int count = 0;
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                count += enemies[i].amountOfConsonants();
            }
        }
        return new Response(false, "Nivel " + (number + 1) + " - Total de consonantes encontradas: " + count + "\n",
                count);
    }

    @Override
    public String toString() {
        String msg = "\nNivel " + (number + 1) +" - Dificultad: " + difficultyAsString() +  "\n";
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

        msg += "\nPuntaje total en el nivel: " + totalLevelScore() + "\n";
        msg += consonantsInEnemies().getMessage() + "\n";
        return msg;
    }

    /**
     * Evaluates if a name is repeated on enemies and return it's number of enemy in
     * roman numbers. Ex :
     * Names of enemies = {'a_I', 'a_II', 'a_III'}
     * getEnemyNumber('a') will return : 'IV'
     * getEnemyNumber('b') will return : 'I'
     * 
     * @param enemyName name of the enemy to look for
     * @return roman number of the enemy name.
     */
    private String getEnemyNumber(String enemyName) {
        int count = 0;
        String msg = "";
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                if (enemies[i].getName().startsWith(enemyName)) {
                    count++;
                }
            }
        }
        while (count >= 10) {
            msg += EnemyType.ROMAN_NUMBERS[9];
            count -= 9;
        }
        msg += EnemyType.ROMAN_NUMBERS[count];
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
