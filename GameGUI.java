import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameGUI extends Pane {
    private ScrollPane scrollPane;
    private VBox content;
    private Pane pane2 = new Pane();
    private Stage stage;
    private Scene mainMenuScene;

    public GameGUI(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        this.mainMenuScene = mainMenuScene;

        content = new VBox();
        scrollPane = new ScrollPane(content);
        scrollPane.setLayoutX(30);
        scrollPane.setLayoutY(460);
        scrollPane.setPrefSize(1000, 180);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(240);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        scrollPane.setBackground(new Background(backgroundFill));

        Button returnBtn = createButton("Return", 14, 28, 130, 60);
        Button button2 = createButton("Save", 920, 28, 130, 60);
        Button button3 = createButton("Draw", 350, 200, 130, 210);
        Button newButton = createButton("New Button", 550, 120, 130, 60);

        pane2.getChildren().addAll(returnBtn, button2, button3);
        content.getChildren().addAll(newButton);
        getChildren().addAll(scrollPane, pane2);
    }

    private Button createButton(String buttonText, double layoutX, double layoutY, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setOnAction(event -> stage.setScene(mainMenuScene));
        return button;
    }
}
