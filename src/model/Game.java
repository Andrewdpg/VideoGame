package model;

import ui.Reader;

public class Game {

    public static int[] SCORES_FOR_LEVEL = new int[GameState.MAX_LEVEL];

    private Player[] players;
    private Level[] levels;

    private Response response;

    public Game() {
        players = new Player[GameState.MAX_PLAYERS];
        levels = new Level[GameState.MAX_LEVEL];
        response = new Response();
    }

    /**
     * Initializes the game with random enemies (the half of maximun) and treasures
     * (the half of maximun). After initializes, it "reevaluates the game".
     * 
     * @return A response with the data of the game.
     */
    public Response initGame() {

        int enemiesToGenerate = GameState.MAX_ENEMIES;
        int treasuresToGenerate = GameState.MAX_TREASURES;

        for (int i = 0; i < GameState.MAX_LEVEL; i++) {
            levels[i] = new Level(i);
        }

        while (enemiesToGenerate > 0) {
            levels[(int) (Math.random() * GameState.MAX_LEVEL)].addEnemy(Enemy.randEnemy());
            enemiesToGenerate--;
        }

        while (treasuresToGenerate > 0) {
            levels[(int) (Math.random() * GameState.MAX_LEVEL)].addTreasure(Treasure.randTreasure());
            treasuresToGenerate--;
        }

        reevaluateGame();
        response.setResponse(false, toString(), null);

        return response;
    }

    /**
     * Reevaluates the difficulties of the different levels in game and recalculates
     * the minimun scores for each level up of players.
     */
    public void reevaluateGame() {

        int totalGameScore = 0;

        for (int i = 0; i < GameState.MAX_LEVEL; i++) {
            levels[i].calculateDifficulty();

            totalGameScore += levels[i].totalLevelScore();
            SCORES_FOR_LEVEL[i] = (int) (totalGameScore * 0.7);

        }

        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                players[i].evaluateLevel();
            }
        }

    }

    /**
     * Generates a new player object with default values, asking to user for its
     * name
     * and nickname.
     * 
     * @return generated player object.
     */
    public Player newPlayer() {

        System.out.print("Nombre: ");
        String name = Reader.readLine();
        System.out.print("Apodo (unico): ");
        String nickname = Reader.readLine();

        return new Player(nickname, name);
    }

    /**
     * Register a new player in case there is space for it.
     * 
     * @return A response with the resulting operation.
     */
    public Response registerPlayer() {

        Player newPlayer = newPlayer();
        newPlayer.evaluateLevel();
        response.setResponse(false, Response.MAX_CAPACITY, null);

        for (int i = 0; i < players.length && !response.isError(); i++) {
            if (players[i] == null) {
                players[i] = newPlayer;
                response.setMessage("Jugador añadido.");
                response.setData(newPlayer);
                i = players.length;
            } else if (players[i].getNickname().equalsIgnoreCase(newPlayer.getNickname())) {
                response.setMessage(Response.ALREADY_EXIST);
                response.setError(true);
            }
        }

        return response;

    }

    /**
     * Searches for a player by its nickname.
     * 
     * @param nickname the player to look for.
     * @return A response with the resulting operation. In case of the player is
     *         found, a message
     *         with the player's information and, on the other hand, it's position
     *         in the array as data.
     */
    public Response searchPlayerByNickname(String nickname) {

        response.setResponse(true, Response.PLAYER_NOT_FOUND, null);

        for (int pos = 0; pos < players.length && response.isError(); pos++) {
            if (players[pos] != null) {
                if (players[pos].getNickname().equals(nickname)) {
                    response.setResponse(false, players[pos].toString(), pos);
                }
            }
        }

        return response;
    }

    /**
     * Calculates the total of enemies in game.
     * 
     * @return total of enemies.
     */
    public int totalEnemies() {
        int count = 0;
        for (int i = 0; i < levels.length; i++) {
            count += levels[i].totalEnemies();
        }
        return count;
    }

    /**
     * Calculates the total of treasures in game.
     * 
     * @return total of treasures.
     */
    public int totalTreasures() {
        int count = 0;
        for (int i = 0; i < levels.length; i++) {
            count += levels[i].totalTreasures();
        }
        return count;
    }

    /**
     * Registers an enemy at a selected level
     * 
     * @param selectedEntity   Type of enemy to register.
     * @param enemyName   Name of the enemy to register.
     * @param atLevel the selected level for the enemy.
     * @return A response with the resultin operation. In case of success, with a
     *         message containing the information of the level.
     */
    public Response registerEnemyAt(int selectedEntity, String enemyName, int atLevel) {
        Enemy enemy = new Enemy(selectedEntity,enemyName);
        response.setResponse(true, Response.NOT_VALID_ENTITY, null);

        if (enemy != null) {
            levels[atLevel].addEnemy(enemy);
            reevaluateGame();
            response.setResponse(false, levels[atLevel].toString(), null);
        }

        return response;
    }

    /**
     * Registers an treasure at a selected level
     * 
     * @param selectedEntity   Type of treasure to register.
     * @param atLevel  the selected level for the treasure.
     * @return A response with the resultin operation. In case of success, with a
     *         message containing the information of the level.
     */
    public Response registerTreasureAt(int selectedEntity, int atLevel) {
        Treasure treasure  = new Treasure(selectedEntity);
        response.setResponse(true, Response.NOT_VALID_ENTITY, null);

        if (treasure != null) {
            levels[atLevel].addTreasure(treasure);
            reevaluateGame();
            response.setResponse(false, levels[atLevel].toString(), null);
        }

        return response;
    }

    /**
     * Modifies the score of a player by reading a new score via console.
     * 
     * @param nickname nickname of the player to modify.
     * @return A response with the resulting operation. In case of success, a
     *         message with player's information.
     */
    public Response modifyScore(String nickname) {

        response = searchPlayerByNickname(nickname);

        if (!response.isError()) {
            int pos = (int) response.getData();
            System.out.println(response.getMessage());

            System.out.println("Nuevo puntaje: ");
            int newScore = Reader.readBetween(0, Integer.MAX_VALUE);

            players[pos].changeScore(newScore);
            response.setResponse(false, players[pos].toString(), null);
        }

        return response;
    }

    /**
     * Requests for the needed score of a player in order to level up.
     * 
     * @param nickname nickname of the player to look for.
     * @return A response with the resulting operation.
     */
    public Response getNeededScore(String nickname) {
        response = searchPlayerByNickname(nickname);

        if (!response.isError()) {
            int pos = (int) response.getData();
            response.setResponse(false,
                    "El jugador necesita: " + players[pos].neededScore() + " puntos para subir de nivel.", null);
        }

        return response;
    }

    /**
     * Looks for all the treasures of an specified type in game.
     * 
     * @param type type of treasure to look for.
     * @return A response with the resulting operation. A message with all of the
     *         treasures information. Also, the amount of treasures found, as data.
     */
    public Response getTreasuresOfType(int type) {

        int count = 0;
        String msg = "";
        Response tempResponse = new Response();

        for (int i = 0; i < levels.length; i++) {
            tempResponse = levels[i].countOfTreasures(type);
            count += (int) tempResponse.getData();
            msg += tempResponse.getMessage();
        }

        tempResponse.setResponse(false, msg, count);

        return tempResponse;
    }

    /**
     * Looks for all the enemies of an specified type in game.
     * 
     * @param type type of enemy to look for.
     * @return A response with the resulting operation. A message with all of the
     *         enemies information. Also, the amount of enemies found, as data.
     */
    public Response getEnemiesOfType(int type) {

        int count = 0;
        String msg = "";
        Response tempResponse = new Response();

        for (int i = 0; i < levels.length; i++) {
            tempResponse = levels[i].countOfEnemies(type);
            count += (int) tempResponse.getData();
            msg += tempResponse.getMessage();
        }

        tempResponse.setResponse(false, msg, count);

        return tempResponse;
    }

    /**
     * Looks for the most repeated treasure in game.
     * 
     * @return A response with the result of the operation. A message with the
     *         information of all found treasures. Also, the amount of these
     *         treasures found, as data.
     */
    public Response searchMostRepeatedTreasure() {

        int higher = 0;
        String msg = "";

        for (int i = 0; i < TreasureType.TREASURE_NAMES.length; i++) {

            if (((int) getTreasuresOfType(i).getData()) > higher) {
                response = getTreasuresOfType(i);
                higher = (int) response.getData();
                msg = response.getMessage() + "\nTotal de tesoros " + TreasureType.TREASURE_NAMES[i] + " encontrados: "
                        + higher;
                response.setResponse(false, msg, higher);
            }
        }

        return response;
    }

    /**
     * Looks for the first enemy in game with the highest score.
     * 
     * @return A response with the result of the operation. A message with the
     *         information of the enemy. Also, its score as data.
     */
    public Response searchHigherScoreEnemy() {
        int higher = 0;
        response.setResponse(true, Response.ENTITY_NOT_FOUND, null);
        for (int i = 0; i < levels.length; i++) {
            if (((int) levels[i].higherScoreEnemy().getData()) > higher) {
                response = levels[i].higherScoreEnemy();
                higher = (int) response.getData();
            }
        }
        return response;
    }

    /**
     * List a part of the top players in game.
     * 
     * @param quantity The amount of players to show.
     * @return String with all of these player's information
     */
    public Response topPlayers(int quantity) {
        Player[] topPlayers = new Player[quantity];
        String msg = "";
        boolean isAdded = false;
        int lowest;
        int lowestPos;
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                isAdded = false;
                lowest = Integer.MAX_VALUE;
                lowestPos = -1;
                for (int j = 0; j < quantity && !isAdded; j++) {
                    if (topPlayers[j] == null) {
                        topPlayers[j] = players[i];
                        isAdded = true;
                    } else {
                        if (topPlayers[j].getScore() < lowest) {
                            lowest = topPlayers[j].getScore();
                            lowestPos = j;
                        }
                    }
                }
                if (!isAdded && players[i].getScore() > lowest) {
                    topPlayers[lowestPos] = players[i];
                }
            }
        }
        for (int i = 0; i < quantity; i++) {
            if (topPlayers[i] != null) {
                msg += topPlayers[i].toString();
            }
        }

        response.setResponse(false, msg, null);

        return response;
    }

    /**
     * Looks for consonants in the names ofall ofthe game enemies.
     * 
     * @return A response with the result of the operation. A message about the
     *         consontants found. Also, its total as data.
     */
    public Response countEnemyConsonants() {
        int count = 0;
        String msg = "";
        for (int i = 0; i < levels.length; i++) {
            response = levels[i].consonantsInEnemies();
            count += (int) response.getData();
            msg += response.getMessage();
        }
        msg += "\n Total de consonantes encontradas en el juego: " + count;
        response.setResponse(false, msg, count);
        return response;
    }

    /**
     * List all the players in game.
     * 
     * @return String with all of player's information
     */
    public Response listPlayers() {
        String msg = "";
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                msg += players[i].toString();
            }
        }
        response.setResponse(false, msg, null);
        return response;
    }

    @Override
    public String toString() {
        String msg = "";
        for (int i = 0; i < GameState.MAX_LEVEL; i++) {
            if (levels[i] != null) {
                msg += levels[i].toString();
            }
        }
        msg+= "Total de enemigos: " + totalEnemies()+"\n"+
        "Total de tesoros: " + totalTreasures() + "\n";
        return msg;
    }

    /**
     * Retreaves a level's information.
     * 
     * @param selectedLevel level to look for.
     * @return String with level's data.
     */
    public String toString(int selectedLevel) {
        return levels[selectedLevel].toString();
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] levels) {
        this.levels = levels;
    }
}