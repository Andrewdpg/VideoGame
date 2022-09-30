package model;

import ui.Reader;

public class Game {

    public static int[] SCORES_FOR_LEVEL = new int[GameState.MAX_LEVEL];

    private Player[] players;
    private Level[] levels;

    private Response response;
    private int currentLevel;

    public Game() {
        players = new Player[GameState.MAX_PLAYERS];
        levels = new Level[GameState.MAX_LEVEL];
        response = new Response();
    }

    public void asignScreenSize() {

        int option = Reader.readBetween(1, 7);

        switch (option) {
            case 1:
                GameState.width = 640;
                GameState.height = 480;
                break;
            case 2:
                GameState.width = 960;
                GameState.height = 540;
                break;
            case 3:
                GameState.width = 1280;
                GameState.height = 720;
                break;
            case 4:
                GameState.width = 1920;
                GameState.height = 1080;
                break;
            case 5:
                GameState.width = 2560;
                GameState.height = 1440;
                break;
            case 6:
                GameState.width = 3840;
                GameState.height = 2160;
                break;
            case 7:
                GameState.width = 7680;
                GameState.height = 4320;
                break;
            default:
                System.out.println("Opción no válida, resolución por defecto: HD");
                GameState.width = 1280;
                GameState.height = 720;
                break;
        }
    }

    public Response initGame() {

        int enemiesToGenerate = GameState.MAX_ENEMIES / 2;
        int treasuresToGenerate = GameState.MAX_TREASURES / 2;

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

    public void reevaluateGame() {

        int totalGameScore = 0;

        for (int i = 0; i < GameState.MAX_LEVEL; i++) {
            levels[i].calculateDifficulty();

            totalGameScore += levels[i].totalLevelScore();
            SCORES_FOR_LEVEL[i] = (int) (totalGameScore * 0.7);

        }

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
                    response.setResponse(false, players[pos].toString(), pos);
                }
            }
        }

        return response;
    }

    public Response registerEnemyAt(Enemy enemy, int atLevel) {

        response.setResponse(true, "Enemigo no valido", null);

        if (enemy != null) {
            levels[atLevel].addEnemy(enemy);
            reevaluateGame();
            response.setResponse(false, levels[atLevel].toString(), null);
        }        

        return response;
    }

    public Response registerTreasureAt(Treasure treasure, int atLevel) {

        response.setResponse(true, "Tesoro no valido", null);

        if (treasure != null) {
            levels[atLevel].addTreasure(treasure);
            reevaluateGame();
            response.setResponse(false, levels[atLevel].toString(), null);
        }

        return response;
    }

    public Response modifyScore(String nickname) {

        response = searchPlayerByNickname(nickname);

        if (!response.isError()) {
            int pos = (int) response.getData();
            System.out.println(response.getMessage());

            System.out.println("Nuevo puntaje: ");
            int newScore = Reader.readNaturalNumber();

            players[pos].changeScore(newScore);
            response.setResponse(false, players[pos].toString(), null);
        }

        return response;
    }

    public Response levelUp(String nickname) {
        response = searchPlayerByNickname(nickname);

        if (!response.isError()) {
            int pos = (int) response.getData();
            players[pos].addToScore(players[pos].neededScore());
            response.setResponse(false, players[pos].toString(), null);
        }

        return response;
    }

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