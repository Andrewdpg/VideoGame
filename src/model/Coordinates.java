package model;

public class Coordinates {

    public static int generateRandX(){
        return (int) (Math.random() * GameState.width);
    }

    public static int generateRandY(){
        return (int) (Math.random() * GameState.height);
    }
}
