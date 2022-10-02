package ui;

import java.util.Scanner;

import model.Response;

public class Reader {

    private static Scanner scn = new Scanner(System.in);

    /**
     * Reads a valid (not empty) String line via console.
     * 
     * @return read String
     */
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

    /**
     * Reads a valid (not empty) String line via console. Excludes a given String
     * 
     * @return read String
     */
    public static String readLine(String excludes) {
        String line = "";

        while (line.equals("") || line.contains(excludes)) {
            try {
                line = scn.next();
            } catch (Exception e) {
                System.out.println(Response.NOT_VALID_VALUE);
                scn.nextLine();
            }

            if (line.contains(excludes)) {
                System.out.println("No debe contener: " + excludes);
            }
        }

        return line;
    }

    /**
     * Reads an int value via console.
     * 
     * @param def default value in case there is an error at reading.
     * @return read value, or default value in case of error.
     */
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

    /**
     * Read an int value between to given numbers
     * 
     * @param min min value to read
     * @param max max value to read
     * @return read value
     */
    public static int readBetween(int min, int max) {
        int value = min - 1;

        if (min <= max) {
            while (value < min || value > max) {
                try {
                    value = scn.nextInt();
                } catch (Exception e) {
                    System.out.println(Response.NOT_VALID_VALUE);
                    scn.nextLine();
                }

                if (value < min || value > max) {
                    System.out.println("Debe ser un valor entre " + min + " y " + max);
                }
            }
        }

        return value;
    }

}
