import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Set up the main menu scene
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 1080, 720);
        stage.setResizable(false);

        // Set background for the menu
        Image icon = new Image("Image/Go Boom Logo.png");
        Image bg = new Image("Image/Go Boom Background.png");
        stage.getIcons().add(icon);
        BackgroundImage background = new BackgroundImage(bg, null, null, null, null);
        Background bg1 = new Background(background);

        pane.setBackground(bg1);
        stage.setTitle("GO-BOOM!!");

        // Load font
        Font font = Font.loadFont(getClass().getResourceAsStream("Font/PokerInOctoberDemo.otf"), 100);

        // Set text for the game
        Text text = new Text();
        text.setText("MENU");
        text.setFont(font);
        text.setTranslateY(-200);
        pane.getChildren().add(text);

        // Set buttons for the menu
        ImageView playImg = new ImageView(new Image("Image/bomb icon.png"));
        Button playBtn = createButton("Play", playImg);
        playBtn.setOnAction(event -> {
            Scene scene2 = new Scene(new GameGUI(stage, scene), 1080, 720);
            stage.setScene(scene2);
        });
        playBtn.setTranslateY(-100);
        pane.getChildren().add(playBtn);

        ImageView resumeImg = new ImageView(new Image("Image/play icon.png"));
        Button resumeBtn = createButton("Resume", resumeImg);
        resumeBtn.setOnAction(event -> {
            System.out.println("Resume Button clicked!");
        });
        pane.getChildren().add(resumeBtn);

        ImageView quitImg = new ImageView(new Image("Image/quit icon.png"));
        Button quitBtn = createButton("Quit", quitImg);
        quitBtn.setOnAction(event -> {
            Platform.exit();
        });
        quitBtn.setTranslateY(100);
        pane.getChildren().add(quitBtn);

        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String buttonText, ImageView imageView) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setPrefSize(200, 50);
        return button;
    }

}
