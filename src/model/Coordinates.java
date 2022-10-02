package model;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates() {
        this.x = generateRandX();
        this.y = generateRandY();
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Generates a random x value between 0 and the current width of the game
     * 
     * @return generated value
     */
    public static int generateRandX() {
        return (int) (Math.random() * GameState.size.getWidth());
    }

    /**
     * Generates a random y value between 0 and the current height of the game
     * 
     * @return generated value
     */
    public static int generateRandY() {
        return (int) (Math.random() * GameState.size.getHeight());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLocationTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
