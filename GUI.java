import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.FontPosture;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GUI extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        
        Group root = new Group();
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 1080, 720);
        
        Image icon = new Image("Image/logo.png");
        Image bg = new Image("Image/bg.jpg");
        stage.getIcons().add(icon);
        BackgroundImage background = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg1 = new Background(background);
        pane.setBackground(bg1);
        stage.setTitle("GO-BOOM!!");

        Text whitestuff = new Text();
        whitestuff.setText("MENU");
        whitestuff.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 90));
        whitestuff.setFill(Color.WHITE);
        whitestuff.setStroke(Color.BLACK);
        whitestuff.setStrokeWidth(2);
        whitestuff.setTranslateY(-250);
        pane.getChildren().add(whitestuff);

        Button playBtn = new Button("Play");
        playBtn.setPrefSize(100, 50);
        playBtn.setTranslateY(-100);
        playBtn.setStyle("-fx-background-color: #FFFFF; -fx-text-fill: #000000; -fx-font-size: 20px;");
        playBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button clicked!");
            }
        });
        pane.getChildren().add(playBtn);

        
        stage.setScene(scene);
        stage.show();
    }
    
}
