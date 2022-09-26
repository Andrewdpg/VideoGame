package model;

import java.awt.*;

public class Coordinates {

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;
    
    public static int generateRandX(){
        return (int) (Math.random() * screenSize.getWidth());
    }

    public static int generateRandY(){
        return (int) (Math.random() * screenSize.getHeight());
    }
}
