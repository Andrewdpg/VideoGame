package model;

public class Treasure {

    private int type;
    private int state;

    private Coordinates location;

    public Treasure(int type) {

        this.type = type;
        this.state = TreasureType.STATE_NOT_FOUND;

        this.location = new Coordinates();
    }

    /**
     * Changes its location to another x and y.
     * 
     * @param x value x on screen.
     * @param y value y on screen.
     */
    public void setLocationTo(int x, int y) {
        this.location.setLocationTo(x, y);
    }

    /**
     * To know if the treasure has not been found yet.
     * 
     * @return True in case it is not taken. False otherwise.
     */
    public boolean isNotTaken() {
        return this.state == TreasureType.STATE_NOT_FOUND;
    }

    @Override
    public String toString() {
        return "Tesoro: " + TreasureType.TREASURE_NAMES[type] + ", Puntos: " + TreasureType.SCORES_FOR_TREASURE[type]
                + ", Estado: "
                + (state == 0 ? "No encontrado" : "Encontrado")
                + ", Posición: " + "(x:" + location.getX()
                + ",y:" + location.getY() + ")\n";
    }

    public int getScore() {
        return TreasureType.SCORES_FOR_TREASURE[type];
    }

    public String getImage() {
        return TreasureType.TREASURE_IMAGES[type];
    }

    public int getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Coordinates getLocation() {
        return location;
    }

    /**
     * Generates a random treasure
     * 
     * @return treasure generated
     */
    public static Treasure randTreasure() {
        return new Treasure((int) (Math.random() * 4));
    }

}
