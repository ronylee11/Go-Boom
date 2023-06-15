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
import java.util.List;

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
    private Game game = new Game();
    private List<HBox> playerHandBoxes;
    private int currentPlayerIndex;

    public GameGUI(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        this.mainMenuScene = mainMenuScene;
        this.playerHandBoxes = new ArrayList<>();
        this.currentPlayerIndex = 0;

        content = new VBox(10); // Set spacing between cards

        scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(240);
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        scrollPane.setBackground(new Background(backgroundFill));

        Button returnBtn = createButton("Return", 14, 28, 130, 60);
        Button button2 = createButton("Save", 920, 28, 130, 60);

        pane2.getChildren().addAll(returnBtn, button2);
        getChildren().addAll(scrollPane, pane2);

        // Add player hand HBox instances to the content VBox
        setupPlayerHands();

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

        pane2.getChildren().add(createdrawView());
    }

    // Image view of draw
    private ImageView createdrawView() {
        Image drawImage = new Image(Main.class.getResourceAsStream("Image/back side of card.png"));
        ImageView drawView = new ImageView(drawImage);
        drawView.setFitHeight(150);
        drawView.setFitWidth(107);
        drawView.setLayoutX(350);
        drawView.setLayoutY(250);
        drawView.setOnMouseClicked((event) -> {
            System.out.println("Player " + currentPlayerIndex + " draws a card!");
        });

        return drawView;
    }

    private void handleCardClick() {
        // Get the current player's hand
        HBox currentPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
    
        // Clear the current player's hand
        currentPlayerHandBox.getChildren().clear();
    
        // Rotate to the next player
        currentPlayerIndex = (currentPlayerIndex + 1) % playerHandBoxes.size();
    
        // Get the next player's hand
        HBox nextPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
    
        // Add the next player's hand to the content VBox
        content.getChildren().set(0, nextPlayerHandBox);
    
        // Check if all players have taken their turns
        if (currentPlayerIndex == 0) {
            // All players have taken their turns, go back to the first player
            currentPlayerIndex = 0;
            HBox firstPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
            content.getChildren().set(0, firstPlayerHandBox);
        }
    }
    

    private Button createButton(String buttonText, double layoutX, double layoutY, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setOnAction(event -> stage.setScene(mainMenuScene));
        return button;
    }

    private void setupPlayerHands() {
        game.initializeGame();
        ArrayList<ArrayList<String>> playerCards = game.getPlayerCards();

        for (int i = 0; i < 4; i++) {
            ArrayList<String> playerHand = playerCards.get(i);
            HBox playerHandBox = createPlayerHandBox(playerHand, i);
            playerHandBoxes.add(playerHandBox);
        }

        // Add the first player's hand to the content VBox
        content.getChildren().add(playerHandBoxes.get(0));
    }

    private HBox createPlayerHandBox(ArrayList<String> hand, int players) {
        HBox hBox = new HBox(10); // Set spacing between cards
        hBox.setAlignment(Pos.CENTER); // Center the cards horizontally within the HBox

        for (String card : hand) {
            // Create ImageView for each card
            ImageView cardView = createCardImageView(card, players);

            // Add cardView to the HBox
            hBox.getChildren().add(cardView);
        }

        return hBox;
    }

    private ImageView createCardImageView(String card, int player) {
        String imagePath = "Image/" + card + ".png";
        Image cardImage = new Image(Main.class.getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(120);
        cardView.setFitWidth(95);
        HBox.setMargin(cardView, new Insets(0, -20, 0, -30));

        cardView.setOnMouseClicked((event) -> {
            System.out.println("Player " + player + " plays " + card + "!");
            handleCardClick();
        });

        return cardView;
    }
}
