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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.FontPosture;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GUI extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        //set scenes
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 1080, 720);

        //set background for menu
        Image icon = new Image("Image/Go Boom Logo.png");
        Image bg = new Image("Image/Go Boom Background.png");
        stage.getIcons().add(icon);
        BackgroundImage background = new BackgroundImage(bg, null,null,null,null);
        Background bg1 = new Background(background);
        ImageView playImg = new ImageView(new Image("Image/bomb icon.png"));
        ImageView resumeImg = new ImageView(new Image("Image/play icon.png"));
        ImageView quitImg = new ImageView(new Image("Image/quit icon.png"));

        pane.setBackground(bg1);
        stage.setTitle("GO-BOOM!!");

        //load font
        Font font = Font.loadFont(getClass().getResourceAsStream("Font/PokerInOctoberDemo.otf"),100);
        
        
        //set text for game
        Text text = new Text();
        text.setText("MENU");
        text.setFont(font);

        //text.setFill(Color.WHITE);
        //text.setStroke(Color.BLACK);
        //text.setStrokeWidth(2);

        text.setTranslateY(-200);
        pane.getChildren().add(text);

        //set buttons for menu
        Button playBtn = new Button("Play");
        playBtn.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");
        //playBtn.setStyle("-fx-background-color: #ff0000; ");
        playBtn.setGraphic(playImg);
        playBtn.setContentDisplay(ContentDisplay.LEFT);
        
        playBtn.setPrefSize(100, 50);
        playBtn.setTranslateY(-100);
        playBtn.setOnAction(event -> {
            Scene scene2 = new Scene(new GameGUI(stage, scene), 1080, 720);
            stage.setScene(scene2);
        });
        pane.getChildren().add(playBtn);

        Button resumeBtn = new Button("Resume");
        resumeBtn.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");
        resumeBtn.setGraphic(resumeImg);
        resumeBtn.setContentDisplay(ContentDisplay.LEFT);
        resumeBtn.setPrefSize(150, 50);
        resumeBtn.setTranslateY(0);
        resumeBtn.setOnAction(event -> {
            System.out.println("Resume Button clicked!");
        });
        pane.getChildren().add(resumeBtn);
        
        Button quitBtn = new Button("Quit");
        quitBtn.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");
        quitBtn.setGraphic(quitImg);
        quitBtn.setContentDisplay(ContentDisplay.LEFT);
        quitBtn.setPrefSize(100, 50);
        quitBtn.setTranslateY(100);
        quitBtn.setOnAction(event -> {
            Platform.exit();
        });
        pane.getChildren().add(quitBtn);

        
        stage.setScene(scene);
        stage.show();
    }
    
}
