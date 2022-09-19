package model;

import ui.Reader;
import ui.Response;

public class Game {

    public static final String NAME = "YOQUESE";
    public static final int MAX_PLAYERS = 20;

    private Player[] players;
    private Response response;

    public Game() {
        players = new Player[MAX_PLAYERS];
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
                    response.setResponse(false, players[pos].getInfo(), pos);
                }
            }
        }

        return response;
    }

    public Response modifyScore() {

        System.out.print("Apodo del jugador a modificar: ");
        String nickname = Reader.readLine();

        response = searchPlayerByNickname(nickname);
        int pos = response.getData() != null? (int) response.getData(): 0;

        if(!response.isError()){
            System.out.println(response.getMessage());

            System.out.println("Nuevo puntaje: ");
            int newScore = Reader.readScore();

            players[pos].changeScore(newScore);
            response.setResponse(false, players[pos].getInfo(), null);
        }

        return response;
    }
}
