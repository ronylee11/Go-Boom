import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        Game gamer = new Game();

        // Set background for the menu
        Image icon = new Image("Image/Go Boom Logo.png");
        Image bg = new Image("Image/Go Boom Background3.jpg");
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
        text.setFill(Color.WHITE);
        text.setTranslateY(-200);
        pane.getChildren().add(text);

        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(5000));
        fade.setFromValue(0.1);
        fade.setToValue(10.0);
        fade.setCycleCount(1000);
        fade.setAutoReverse(true);
        fade.setNode(text);
        fade.play();

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
            gamer.load();
            Scene scene2 = new Scene(new GameGUI(stage, scene), 1080, 720);
            stage.setScene(scene2);
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
        Font btnFont = Font.loadFont(getClass().getResourceAsStream("Font/2156-font.otf"), 18);
        button.setFont(btnFont);
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setPrefSize(200, 50);

        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
        });

        button.setOnMouseExited(event -> {
        button.setScaleX(1.0);
        button.setScaleY(1.0);
        });

        button.setOnMouseClicked(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.8);
        scaleTransition.setToY(0.8);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play(); 
    });
        
        return button;
    }

}
