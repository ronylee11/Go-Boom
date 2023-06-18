import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the game of Go-Boom!");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.print("> ");

        try {
            int choice = input.nextInt();
            Game game = new Game();

            switch (choice) {
                case 1:
                    System.out.println("");
                    game.start();
                case 2:
                    System.out.println("");
                    game.load();
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Try again (1~3)");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again (1~3)");
        }
        input.close();

        // clearScreen();
        // System.out.println("Game Over! " + text);
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
}
