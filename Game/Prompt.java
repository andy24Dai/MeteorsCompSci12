package Game;

import java.io.*;
import java.util.*;

public class Prompt {
    private static Scanner sc = new Scanner(System.in);

    // ******************** User Input **************************
    public static int getInt() {
        int n;

        while (!sc.hasNextInt()) {
            String input = sc.next();
            System.out.printf("\"%s\" is not a valid number.%n", input);
        }
        n = sc.nextInt();
        sc.nextLine();
        return n;
    }// getInt

    public static int getInt(String prompt) {
        int n;

        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            String input = sc.next();
            System.out.printf("\"%s\" is not a valid number.%n", input);
            System.out.print(prompt);
        }
        n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public static int getInt(String prompt, int min, int max) {
        int n = 0;
        boolean hasValidInput = false;

        while (!hasValidInput) {
            System.out.print(prompt);
            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.printf("\"%s\" is not a valid number.%n", input);
                System.out.print(prompt);
            }
            n = sc.nextInt();
            sc.nextLine();

            if (n >= min && n <= max) {
                hasValidInput = true;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        } // while
        return n;
    }

    public static double getDouble() {
        double n;

        while (!sc.hasNextDouble()) {
            String input = sc.next();
            System.out.printf("\"%s\" is not a valid input.%n", input);
        }
        n = sc.nextDouble();
        sc.nextLine();
        return n;
    }// getDouble

    public static double getDouble(String prompt) {
        double n;

        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            String input = sc.next();
            System.out.printf("\"%s\" is not a valid input.%n", input);
            System.out.print(prompt);
        }
        n = sc.nextDouble();
        sc.nextLine();
        return n;
    }

    public static String getDoubleWithExitString(String prompt, String exit) {
        String s;
        boolean isDouble = false;

        while (true) {
            System.out.print(prompt);
            isDouble = sc.hasNextDouble();
            s = sc.nextLine();
            if (s.equals("")) {
                System.out.println("Please enter a value");
                continue;
            } else if (isDouble) {
                return s;
            } else if (s.equals(exit)) {
                return s;
            } else {
                System.out.println("Please enter a number or \"" + exit + "\"");
                continue;
            }
        } // while
    }

    public static double getDouble(String prompt, int min, int max) {
        double n = 0;
        boolean hasValidInput = false;

        while (!hasValidInput) {
            System.out.print(prompt);
            while (!sc.hasNextDouble()) {
                String input = sc.next();
                System.out.printf("\"%s\" is not a valid number.%n", input);
                System.out.print(prompt);
            }
            n = sc.nextDouble();
            sc.nextLine();

            if (n >= min && n <= max) {
                hasValidInput = true;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        } // while
        return n;
    }

    public static String getString() {
        String s;

        while (true) {
            s = sc.nextLine();
            if (s.equals("")) {
                System.out.println("Please enter a value");
                continue;
            }
            return s;
        } // while
    }

    public static String getString(String prompt) {
        String s;

        while (true) {
            System.out.print(prompt);
            s = sc.nextLine();
            if (s.equals("")) {
                System.out.println("Please enter a value");
                continue;
            }
            return s;
        } // while
    }

    public static char getChar(String prompt) {
        char ch;
        String input;

        while (true) {
            input = Prompt.getString(prompt);
            if (input.length() != 1) {
                System.out.println("Please enter a single character");
                continue;
            }
            ch = input.charAt(0);

            return ch;
        } // while
    }

    // ******************** File Input **************************
    public static File getInputFile(String fileName) {
        File file = new File(fileName);
        return file;
    }

    public static Scanner getInputScanner(String fileName) {
        String name = fileName;

        while (true) {
            try {
                File file = getInputFile(name);
                return new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File can not be found");
                name = Prompt.getString("Please enter the file name: ");
            }
        }
    }

    public static PrintWriter getPrintWriter(String fileName) {
        String name = fileName;

        while (true) {
            try {
                File file = getInputFile(name);
                return new PrintWriter(file);
            } catch (FileNotFoundException e) {
                System.out.println("File can not be found");
                name = Prompt.getString("Please enter the file name: ");
            }
        }
    }

}// class
