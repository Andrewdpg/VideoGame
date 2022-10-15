package ui;

import model.EnemyType;
import model.Game;
import model.GameState;
import model.Response;
import model.TreasureType;

public class Menu {

    //Menu to be shown to the user
    public static final String MENU_STRING = "\nBienvenido a " + GameState.NAME + "\n\n" +
            "1. Crear un jugador.\n" +
            "2. Registrar enemigo.\n" +
            "3. Registrar tesoro.\n" +
            "4. Modificar puntaje de un jugador.\n" +
            "5. Subir nivel de un jugador.\n" +
            "6. Mostrar informacion de un nivel.\n" +
            "7. Ver total de tesoros de un tipo.\n" +
            "8. Ver total de enemigos de un tipo.\n" +
            "9. Mostrar tesoro más repetido.\n" +
            "10. Mostrar enemigo de mayor puntaje.\n" +
            "11. Ver la cantidad de consonantes en enemigos.\n" +
            "12. Ver top de jugadores.\n" +
            "13. Mostrar informacion del juego.\n" +
            "14. Listar jugadores.\n" +
            "0. Salir.\n" +
            "Opcion: ";

    public static final int MIN_OPTION = 0;
    public static final int MAX_OPTION = 14;

    private boolean stillAlive;
    private int option;
    private Game game;
    private String message;

    public Menu() {
        option = -1;
        stillAlive = true;
        game = new Game();

        GameState.initResolution();
        System.out
                .println("\n\nResolución de pantalla: " + GameState.size.getWidth() + "x" + GameState.size.getHeight());
        game.initGame();
        System.out.println("Parte del juego ha sido inicializada aleatoriamente.");
    }

    /**
     * Checks the state of the program to know if it should stop or keep running
     * 
     * @return True in case the program is still running/alive.
     */
    public boolean stillAlive() {
        return stillAlive;
    }

    public int getOption() {
        return option;
    }

    /**
     * Reads an option (number between MIN_OPTION and MAX_OPTION)
     */
    public void readOption() {
        option = Reader.readBetween(MIN_OPTION, MAX_OPTION);
    }

    /**
     * Displays the menu via console
     */
    public void showMenu() {
        System.out.print(MENU_STRING);
    }

    /**
     * Executes the selected option.
     */
    public void executeOption() {
        String nickname;
        int selectedLevel;
        int selectedEntity;

        switch (option) {
            case 1:
                message = game.registerPlayer().getMessage();
                break;
            case 2:
                System.out.print("Dame el nombre de el nuevo enemigo:");
                String enemyName = Reader.readLine("_");
                System.out.println("¿En qué nivel quieres registrarlo? 1 - " + GameState.MAX_LEVEL);
                selectedLevel = Reader.readBetween(1, GameState.MAX_LEVEL);
                System.out.println("Dame el tipo de enemigo a registrar: \n\n" + EnemyType.LIST_OF_ENEMIES);
                selectedEntity = Reader.readBetween(0, EnemyType.INITIAL_SCORES_FOR_ENEMIES.length - 1);

                message = game.registerEnemyAt(selectedEntity, enemyName, selectedLevel - 1).getMessage();
                break;
            case 3:
                System.out.println("Dame el tipo de Tesoro a registrar: \n\n" + TreasureType.LIST_OF_TREASURES);
                selectedEntity = Reader.readBetween(0, TreasureType.TREASURE_NAMES.length - 1);
                System.out.println("¿En qué nivel quieres registrarlo? 0 - " + GameState.MAX_LEVEL);
                selectedLevel = Reader.readBetween(0, GameState.MAX_LEVEL);

                message = game.registerTreasureAt(selectedEntity, selectedLevel - 1).getMessage();
                break;
            case 4:
                System.out.print("Apodo del jugador a modificar: ");
                nickname = Reader.readLine();
                message = game.modifyScore(nickname).getMessage();
                break;
            case 5:
                System.out.print("Apodo del jugador a subir de nivel: ");
                nickname = Reader.readLine();
                message = game.getNeededScore(nickname).getMessage();
                break;
            case 6:
                System.out.println("¿Que nivel te gustaría ver? 0 - " + GameState.MAX_LEVEL);
                selectedLevel = Reader.readBetween(1, GameState.MAX_LEVEL);
                message = game.toString(selectedLevel - 1);
                break;
            case 7:
                System.out.println("Qué tipo de tesoro te gustaría buscar? \n" + TreasureType.LIST_OF_TREASURES);
                selectedEntity = Reader.readBetween(0, TreasureType.SCORES_FOR_TREASURE.length - 1);
                message = game.getTreasuresOfType(selectedEntity).getMessage();
                break;
            case 8:
                System.out.println("Qué tipo de enemigo te gustaría buscar? \n" + TreasureType.LIST_OF_TREASURES);
                selectedEntity = Reader.readBetween(0, TreasureType.SCORES_FOR_TREASURE.length - 1);
                message = game.getEnemiesOfType(selectedEntity).getMessage();
                break;
            case 9:
                message = game.searchMostRepeatedTreasure().getMessage();
                break;
            case 10:
                message = game.searchHigherScoreEnemy().getMessage();
                break;
            case 11:
                message = game.countEnemyConsonants().getMessage();
                break;
            case 12:
                System.out.println("¿Cuantos jugadores quieres mostrar?");
                int quantity = Reader.readBetween(0, GameState.MAX_PLAYERS);
                message = game.topPlayers(quantity).getMessage();
                break;
            case 13:
                message = game.toString();
                break;
            case 14:
                message = game.listPlayers().getMessage();
                break;
            case 0:
                this.stillAlive = false;
                message = "Adios.";
                break;
            default:
                message = Response.NOT_VALID_OPTION;
                break;
        }
        System.out.println(message);
    }
}
