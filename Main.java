import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        Scanner input = new Scanner(System.in);

        System.out.print("> ");
        String text = input.nextLine();

        // clearScreen();
        // System.out.println("Game Over! " + text);
        input.close();
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
