package model;


public class Treasure {

    private String name;
    private String image;
    private int score;

    private int posX;
    private int posY;

    public Treasure() {
        this.name = String.valueOf(Math.random() * 10000);
        this.image = String.valueOf(Math.random() * 10000);
        this.score = (int) (Math.random() * 100);

        posX = Coordinates.generateRandX();
        posY = Coordinates.generateRandY();
    }

    public void setLocationTo(int x, int y) {
        posX = x;
        posY = y;
    }

    public String getInfo() {
        return "Tesoro: " + name + ", Puntos: " + score + ", Posición: " + "(x:" + posX
                + ",y:" + posY + ")\n";
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Treasure randTreasure() {
        return new Treasure();
    }
}
