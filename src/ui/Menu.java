package ui;

import model.Game;
import model.Response;

public class Menu {

    public static final String MENU_STRING = "\nBienvenido a " + Game.NAME + "\n\n" +
            "1. Crear un jugador.\n" +
            "2. Crear nuevo juego.\n" +
            "3. Mostrar info niveles.\n" +
            "4. Modificar puntaje de un jugador.\n" +
            "5. Subir nivel de un jugador.\n" +
            "Opcion: ";

    private boolean stillAlive;
    private int option;
    private Game game;
    private String message;

    public Menu() {
        option = -1;
        stillAlive = true;
        game = new Game();
    }

    public boolean stillAlive() {
        return stillAlive;
    }

    public int getOption() {
        return option;
    }

    public void readOption() {
        option = Reader.readInt(-1);
    }

    public void showMenu() {
        System.out.print(MENU_STRING);
    }

    public void executeOption() {
        String nickname;

        switch (option) {
            case 1:
                message = game.createPlayer().getMessage();
                break;
            case 2: 
                message = game.generateNewRandomGame().getMessage();
                break;
            case 3:
                message = game.getInfo();
                break;
            case 4:
                System.out.print("Apodo del jugador a modificar: ");
                nickname = Reader.readLine();
                message = game.modifyScore(nickname).getMessage();
                break;
            case 5:
                System.out.print("Apodo del jugador a subir de nivel: ");
                nickname = Reader.readLine();
                message = game.levelUp(nickname).getMessage();
                break;
            default:
                message = Response.NOT_VALID_OPTION;
                break;
        }
        System.out.println(message);
    }
}
