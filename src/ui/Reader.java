package ui;

import java.util.Scanner;

import model.Response;

public class Reader {

    private static Scanner scn = new Scanner(System.in);

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

    public static int readNaturalNumber() {
        int score = -1;

        while (score < 0) {
            try {
                score = scn.nextInt();
            } catch (Exception e) {
                System.out.println(Response.NOT_VALID_VALUE);
                scn.nextLine();
            }

            if (score < 0) {
                System.out.println(Response.MUST_BE_POSITIVE);
            }
        }

        return score;
    }

    public static int readBetween(int min, int max) {
        int option = min - 1;

        if (min <= max) {
            while (option < min || option > max) {
                try {
                    option = scn.nextInt();
                } catch (Exception e) {
                    System.out.println(Response.NOT_VALID_VALUE);
                    scn.nextLine();
                }

                if (option < min || option > max) {
                    System.out.println("Debe ser un valor entre " + min + " y " + max);
                }
            }
        }

        return option;
    }
}
