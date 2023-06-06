import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameGUI extends StackPane {
    private Pane pane2 = new Pane();
    private Stage stage;
    private Scene mainMenuScene;

    public GameGUI(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        this.mainMenuScene = mainMenuScene;
        
        Button returnBtn = createButton("Return", 100, 50);
        pane2.getChildren().add(returnBtn);
        getChildren().add(pane2);
    }

    private Button createButton(String buttonText, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        button.setPrefSize(minWidth, minHeight);
        button.setOnAction(event -> stage.setScene(mainMenuScene));
        return button;
    }
}

