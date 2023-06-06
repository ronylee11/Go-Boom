import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.FontPosture;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


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
        pane.setBackground(bg1);
        stage.setTitle("GO-BOOM!!");
        //set text for game
        Text whitestuff = new Text();
        whitestuff.setText("MENU");
        whitestuff.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 90));
        whitestuff.setFill(Color.WHITE);
        whitestuff.setStroke(Color.BLACK);
        whitestuff.setStrokeWidth(2);
        whitestuff.setTranslateY(-250);
        pane.getChildren().add(whitestuff);
        //set buttons for menu
        Button playBtn = new Button("Play");
        playBtn.setPrefSize(100, 50);
        playBtn.setTranslateY(-100);
        playBtn.setOnAction(event -> {
            Scene scene2 = new Scene(new GameGUI(stage, scene), 1080, 720);
            stage.setScene(scene2);
        });
        pane.getChildren().add(playBtn);

        Button resumeBtn = new Button("Resume");
        resumeBtn.setPrefSize(100, 50);
        resumeBtn.setTranslateY(0);
        resumeBtn.setOnAction(event -> {
            System.out.println("Resume Button clicked!");
        });
        pane.getChildren().add(resumeBtn);
        
        Button quitbtn = new Button("Quit");
        quitbtn.setPrefSize(100, 50);
        quitbtn.setTranslateY(100);
        quitbtn.setOnAction(event -> {
            Platform.exit();
        });
        pane.getChildren().add(quitbtn);

        
        stage.setScene(scene);
        stage.show();
    }
    
}
