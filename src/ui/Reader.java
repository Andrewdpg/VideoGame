package ui;

import java.util.Scanner;

import model.Response;

public class Reader {

    private static Scanner scn = new Scanner(System.in);

    public Reader() {
        
    }

    public static String readLine() {
        String line = "";

        while (line.equals("")) {
            try {
                line = scn.nextLine();
            } catch (Exception e) {
                System.out.println(Response.NOT_VALID_VALUE);
                scn.nextLine();
            }
        }

        return line;
    }

    public static int readInt(int def) {
        int value = def;

        try {
            value = scn.nextInt();
        } catch (Exception e) {
            System.out.println(Response.NOT_VALID_VALUE);
            scn.nextLine();
        }

        return value;
    }

    public static int readScore() {
        int score = -1;

        while (score < 0) {
            try {
                score = scn.nextInt();
            } catch (Exception e) {
                System.out.println(Response.NOT_VALID_VALUE);
                scn.nextLine();
            }

            if(score < 0){
                System.out.println(Response.MUST_BE_POSITIVE);
            }
        }

        return score;
    }
}
