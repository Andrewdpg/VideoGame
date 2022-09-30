package ui;

import model.Enemy;
import model.EnemyType;
import model.Game;
import model.GameState;
import model.Response;
import model.Treasure;
import model.TreasureType;

public class Menu {

    public static final String SCREEN_SIZE_MENU_STRING = "\nSelecciona una resolución:" + "\n\n" +
            "1. SD.\n" +
            "2. QHD.\n" +
            "3. HD.\n" +
            "4. FHD.\n" +
            "5. QHD.\n" +
            "6. UHD.\n" +
            "7. UHD 8K.\n" +
            "Opcion: ";

    public static final String MENU_STRING = "\nBienvenido a " + GameState.NAME + "\n\n" +
            "1. Crear un jugador.\n" +
            "2. Registrar enemigo.\n" +
            "3. Registrar tesoro.\n" +
            "4. Mostrar info niveles.\n" +
            "5. Modificar puntaje de un jugador.\n" +
            "6. Subir nivel de un jugador.\n" +
            "7. Listar jugadores.\n" +
            "0. Salir.\n" +
            "Opcion: ";

    public static final int MIN_OPTION = 0;
    public static final int MAX_OPTION = 7;

    private boolean stillAlive;
    private int option;
    private Game game;
    private String message;

    public Menu() {
        option = -1;
        stillAlive = true;
        game = new Game();

        System.out.print(SCREEN_SIZE_MENU_STRING);
        game.asignScreenSize();

        System.out.println("Juego inicializado como: \n\n" + game.initGame().getMessage());
    }

    public boolean stillAlive() {
        return stillAlive;
    }

    public int getOption() {
        return option;
    }

    public void readOption() {
        option = Reader.readBetween(MIN_OPTION, MAX_OPTION);
    }

    public void showMenu() {
        System.out.print(MENU_STRING);
    }

    public void executeOption() {
        String nickname;
        int selectedLevelToModify;

        switch (option) {
            case 1:
                message = game.createPlayer().getMessage();
                break;
            case 2:
                System.out.println("Dame el tipo de enemigo a registrar: \n\n" + EnemyType.LIST_OF_ENEMIES);
                int enemyType = Reader.readBetween(0, EnemyType.ENEMY_NAMES.length-1);
                System.out.println("En qué nivel quieres registrarlo? 0 - " + GameState.MAX_LEVEL);
                selectedLevelToModify = Reader.readBetween(1, GameState.MAX_LEVEL);

                message = game.registerEnemyAt(new Enemy(enemyType), selectedLevelToModify-1).getMessage();
                break;
            case 3:
                System.out.println("Dame el tipo de Tesoro a registrar: \n\n" + TreasureType.LIST_OF_TREASURES);
                int treasureType = Reader.readBetween(0, TreasureType.TREASURE_NAMES.length-1);
                System.out.println("En qué nivel quieres registrarlo? 0 - " + GameState.MAX_LEVEL);
                selectedLevelToModify = Reader.readBetween(0, GameState.MAX_LEVEL);

                message = game.registerTreasureAt(new Treasure(treasureType), selectedLevelToModify-1).getMessage();
                break;
            case 4:
                message = game.toString();
                break;
            case 5:
                System.out.print("Apodo del jugador a modificar: ");
                nickname = Reader.readLine();
                message = game.modifyScore(nickname).getMessage();
                break;
            case 6:
                System.out.print("Apodo del jugador a subir de nivel: ");
                nickname = Reader.readLine();
                message = game.levelUp(nickname).getMessage();
                break;
            case 7:
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
