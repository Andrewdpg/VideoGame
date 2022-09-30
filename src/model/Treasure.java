package model;

public class Treasure {

    private int type;
    private int state;

    private int posX;
    private int posY;

    public Treasure(int type) {

        this.type = type;
        this.state = TreasureType.STATE_NOT_FOUND;

        posX = Coordinates.generateRandX();
        posY = Coordinates.generateRandY();
    }

    public void setLocationTo(int x, int y) {
        posX = x;
        posY = y;
    }

    @Override
    public String toString() {
        return "Tesoro: " + TreasureType.TREASURE_NAMES[type] + ", Puntos: " + TreasureType.SCORES_FOR_TREASURE[type] + ", Estado: "
                + (state == 0 ? "No encontrado" : "Encontrado")
                + ", Posición: " + "(x:" + posX
                + ",y:" + posY + ")\n";
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public int getScore() {
        return TreasureType.SCORES_FOR_TREASURE[type];
    }

    public String getImage() {
        return TreasureType.TREASURE_IMAGES[type];
    }

    public static Treasure randTreasure() {
        return new Treasure((int) (Math.random() * 4));
    }
}
