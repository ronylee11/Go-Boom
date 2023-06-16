import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the game of Go-Boom!");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.print("> ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("");
                Game game = new Game();
                game.start();
                break;
            case 2:
                // load game
            case 3:
                System.exit(0);
                break;
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
