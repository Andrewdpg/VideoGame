package ui;

import model.Enemy;
import model.Player;

public class Main {

    public static void main(String[] args) {

        Player player = new Player("Andrewpg", "Andrés Parra");
        Enemy enemy = new Enemy(Enemy.BOSS);
        System.out.println(player.getScore());

        enemy.addScore();
        player.gotKilledBy(enemy.getType());
        
        player.addToScore(enemy.killAndDropScore());
        System.out.println(player.getScore());
    }
}
