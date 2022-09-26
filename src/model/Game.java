package model;

import ui.Reader;

public class Game {

    public static final String NAME = "YOQUESE";
    public static final int MAX_PLAYERS = 20;
    public static final int MAX_ENEMIES = 25;
    public static final int MAX_TREASURES = 50;
    public static final int MAX_LEVEL = 10;
    public static int[] SCORES_FOR_LEVEL = new int[MAX_LEVEL];

    private Player[] players;
    private Level[] levels;

    private Response response;
    private int currentLevel;

    public Game() {
        players = new Player[MAX_PLAYERS];
        levels = new Level[MAX_LEVEL];
        response = new Response();
    }

    public Player newPlayer() {

        System.out.print("Nombre: ");
        String name = Reader.readLine();
        System.out.print("Apodo (unico): ");
        String nickname = Reader.readLine();

        return new Player(nickname, name);
    }

    public Response createPlayer() {

        Player newPlayer = newPlayer();
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

    public Response searchPlayerByNickname(String nickname) {

        response.setResponse(true, Response.PLAYER_NOT_FOUND, null);

        for (int pos = 0; pos < players.length && response.isError(); pos++) {
            if (players[pos] != null) {
                if (players[pos].getNickname().equals(nickname)) {
                    response.setResponse(false, players[pos].getInfoToString(), pos);
                }
            }
        }

        return response;
    }

    public Response modifyScore(String nickname) {

        response = searchPlayerByNickname(nickname);

        if (!response.isError()) {
            int pos = (int) response.getData();
            System.out.println(response.getMessage());

            System.out.println("Nuevo puntaje: ");
            int newScore = Reader.readScore();

            players[pos].changeScore(newScore);
            response.setResponse(false, players[pos].getInfoToString(), null);
        }

        return response;
    }

    public Response levelUp(String nickname) {
        response = searchPlayerByNickname(nickname);

        if (!response.isError()) {
            int pos = (int) response.getData();
            players[pos].addToScore(players[pos].neededScore());
            response.setResponse(false, players[pos].getInfoToString(), null);
        }

        return response;
    }

    public Response generateNewRandomGame() {

        int enemiesToGenerate = MAX_ENEMIES;
        int treasuresToGenerate = MAX_TREASURES;
        int totalScoreUntil = 0;

        for (int i = 0; i < MAX_LEVEL; i++) {

            int enemiesInLevel = (int) (Math.random() * enemiesToGenerate);
            int treasuresInLevel = (int) (Math.random() * treasuresToGenerate);

            if(enemiesInLevel > ((int) (MAX_ENEMIES / (MAX_LEVEL - i)))){
                enemiesInLevel = ((int) (MAX_ENEMIES / (MAX_LEVEL - i)));
            }else if(enemiesInLevel == 0 && enemiesToGenerate > 0){
                enemiesInLevel = 1;
            }

            if(treasuresInLevel > ((int) (MAX_TREASURES / (MAX_LEVEL - i)))){
                treasuresInLevel= ((int) (MAX_TREASURES / (MAX_LEVEL - i)));
            }else if(treasuresInLevel == 0 && treasuresToGenerate > 0){
                treasuresInLevel = 1;
            }

            levels[i] = new Level(i);
            levels[i].setEnemies(new Enemy[enemiesInLevel]);
            levels[i].setTreasures(new Treasure[treasuresInLevel]);

            for (int j = 0; j < enemiesInLevel; j++) {
                levels[i].addEnemy(Enemy.randEnemy());
                enemiesToGenerate--;
            }

            for (int j = 0; j < treasuresInLevel; j++) {
                levels[i].addTreasure(Treasure.randTreasure());
                treasuresToGenerate--;
            }

            totalScoreUntil += levels[i].totalLevelScore();
            levels[i].setNextLevelScore((int) (totalScoreUntil * 0.7));
            levels[i].calculateDifficulty();
        }

        response.setResponse(false, getInfo(), null);

        return response;
    }

    public String getInfo() {
        String msg = "";
        for (int i = 0; i < MAX_LEVEL; i++) {
            if (levels[i] != null) {
                msg += levels[i].getInfo();
            }
        }
        return msg;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] levels) {
        this.levels = levels;
    }
}
