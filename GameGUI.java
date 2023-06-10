import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameGUI extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox content;
    private Pane pane2 = new Pane();
    private Stage stage;
    private Scene mainMenuScene;

    public GameGUI(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        this.mainMenuScene = mainMenuScene;

        content = new VBox(10); // Set spacing between cards

        scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(240);
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        scrollPane.setBackground(new Background(backgroundFill));

        Button returnBtn = createButton("Return", 14, 28, 130, 60);
        Button button2 = createButton("Save", 920, 28, 130, 60);
        Button button3 = createButton("Draw", 350, 200, 130, 210);

        pane2.getChildren().addAll(returnBtn, button2, button3);
        getChildren().addAll(scrollPane, pane2);

        // Create player hand HBox instances
        HBox player1HandBox = createPlayerHandBox(player1Hand);
        HBox player2HandBox = createPlayerHandBox(player2Hand);
        HBox player3HandBox = createPlayerHandBox(player3Hand);
        HBox player4HandBox = createPlayerHandBox(player4Hand);

        // Add player hand HBox instances to the content VBox
        content.getChildren().addAll(player1HandBox, player2HandBox, player3HandBox, player4HandBox);

        // Anchor the scrollPane to the bottom of the AnchorPane
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        // Anchor the pane2 to the top-right of the AnchorPane
        AnchorPane.setTopAnchor(pane2, 10.0);
        AnchorPane.setRightAnchor(pane2, 10.0);

        // Bind the size of the content VBox to the width of the scrollPane
        content.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));

        // Scroll to the bottom of the ScrollPane when the content changes
        scrollPane.vvalueProperty().bind(content.heightProperty());
    }


    private Button createButton(String buttonText, double layoutX, double layoutY, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setOnAction(event -> stage.setScene(mainMenuScene));
        return button;
    }


    // for testing purposes
    ArrayList<String> player1Hand = new ArrayList<>(Arrays.asList("hA", "sA", "h2", "sQ", "d4", "s5", "s9", "d7", "h8"));
    ArrayList<String> player2Hand = new ArrayList<>(Arrays.asList("h2", "sA", "c2", "s3", "d4", "d5", "d6", "s7", "s8", "s9", "sX", "sJ", "sQ", "sK"));
    ArrayList<String> player3Hand = new ArrayList<>(Arrays.asList("sA", "cA", "sK", "c3", "sJ", "sX", "sX"));
    ArrayList<String> player4Hand = new ArrayList<>(Arrays.asList("dA", "sK", "s2", "cJ", "c4", "sQ", "s6", "d7", "dX", "sA", "cA"));

    private static HBox createPlayerHandBox(ArrayList<String> hand) {
        HBox hBox = new HBox(10); // Set spacing between cards
        hBox.setAlignment(Pos.CENTER); // Center the cards horizontally within the HBox

        for (String card : hand) {
            // Create ImageView for each card
            ImageView cardView = createCardImageView(card);

            // Add cardView to the HBox
            hBox.getChildren().add(cardView);
        }

        return hBox;
    }

    private static ImageView createCardImageView(String card) {
        String imagePath = "Image/" + card + ".png";
        Image cardImage = new Image(Main.class.getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(120);
        cardView.setFitWidth(95);
        HBox.setMargin(cardView, new Insets(0, -20, 0, -30));

        cardView.setOnMouseClicked((event) -> {
            System.out.println("Play " + card + "!");
        });

        return cardView;
    }
}
