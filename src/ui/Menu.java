package ui;

import model.Game;

public class Menu {

    public static final String MENU_STRING = "\nBienvenido a " + Game.NAME + "\n\n" +
            "1. Crear un jugador.\n"+
            "4. Modificar puntaje de un jugador.\n"+
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

        switch (option) {
            case 1:
                message = game.createPlayer().getMessage();
                break;

            case 4:
                message = game.modifyScore().getMessage();
                break;
            default:
                message = Response.NOT_VALID_OPTION;
                break;
        }
        System.out.println(message);
    }
}
