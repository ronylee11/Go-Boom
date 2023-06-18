import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

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
