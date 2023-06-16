import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<HBox> playerHandBoxes;
    private int currentPlayerIndex;
    private HBox gcenterBox;
    Game game = new Game();
    ArrayList<ArrayList<String>> playerCards;
    ArrayList<String> gcenter = new ArrayList<>();

    public GameGUI(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        this.mainMenuScene = mainMenuScene;
        this.playerHandBoxes = new ArrayList<>();
        
        content = new VBox(10); // Set spacing between cards
        StackPane stackPane = new StackPane();
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
        game.initializeGame();
        game.generateCenter();
        playerCards = game.getPlayerCards();
        gcenter = game.center;
        this.currentPlayerIndex = game.determineFirstPlayer(gcenter.get(0));
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

        
        AnchorPane.setTopAnchor(stackPane, 10.0);

        pane2.getChildren().add(createdrawView());
        pane2.getChildren().add(createGCenterBox());
        content.getChildren().add(1, showPlayerTurn(currentPlayerIndex));


    }

    private ImageView createCardImageView(String card, int player) { //create the card image view
        String imagePath = "Image/" + card + ".png";
        Image cardImage = new Image(Main.class.getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(150);
        cardView.setFitWidth(107);
        HBox.setMargin(cardView, new Insets(0, -20, 0, -30));
    
        cardView.setOnMouseClicked((event) -> {
            System.out.println("Player " + player + " plays " + card + "!");
            handleCardClick();
        });
    
        return cardView;
    }

    // Image view of draw
    private ImageView createdrawView() {
        Image drawImage = new Image(Main.class.getResourceAsStream("Image/back side of card1.jpg"));
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

    private HBox createGCenterBox() {
    HBox centerbox = new HBox(10);  // Set spacing between cards
    centerbox.setAlignment(Pos.CENTER);
    String examplePath = "Image/sA.png";
    //the imageview is for testing purposes only
        Image exImage = new Image(Main.class.getResourceAsStream(examplePath));
        ImageView exView = new ImageView(exImage);
        exView.setFitHeight(150);
        exView.setFitWidth(107);
        centerbox.getChildren().add(exView);
        centerbox.setLayoutX(500);
        centerbox.setLayoutY(250);

    return centerbox;
    }

    private HBox createPlayerHandBox(ArrayList<String> hand, int players) { //create the player hand box
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

    private Button createButton(String buttonText, double layoutX, double layoutY, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setOnAction(event -> {
            stage.setScene(mainMenuScene); // need a way to quit the game after 
                                            // clicking the return button
        });
        return button;
    }

    private Text showPlayerTurn(int currentPlayerIndex) {
        Text playerText = new Text("Player " + (currentPlayerIndex + 1));
        playerText.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");
        playerText.setTranslateY(-150);
        playerText.setTranslateX(20);
        return playerText;
    }

    //get the player card from game
    private void setupPlayerHands() { 
        for (int i = 0; i < 4; i++) { //loop through the 2d arraylist and generate the card
            ArrayList<String> playerHand = playerCards.get(i); //get the player hand
            HBox playerHandBox = createPlayerHandBox(playerHand, i); //create the player hand box
            playerHandBoxes.add(playerHandBox);                  //add the player hand box to the arraylist
        }   

        // Add the first player's hand to the content VBox
        content.getChildren().add(playerHandBoxes.get(0));
    }


    private void handleCardClick() {
        HBox currentPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
        currentPlayerHandBox.getChildren().clear();
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
        HBox nextPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
        content.getChildren().set(0, nextPlayerHandBox);

        Text currentPlayerText = showPlayerTurn(currentPlayerIndex);
        content.getChildren().set(1, currentPlayerText);
        //I got the text to rotate between players but player's turn between
        // the GUI and the text in the terminal doesn't match
        
        if (currentPlayerIndex == 0) {
            currentPlayerIndex = 0;
            HBox firstPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
            content.getChildren().set(0, firstPlayerHandBox);
        }
    
        // Update the current player's hand in the GUI
        ArrayList<String> currentPlayerCards = playerCards.get(currentPlayerIndex);
        currentPlayerHandBox.getChildren().addAll(
                currentPlayerCards.stream()
                        .map(card -> createCardImageView(card, (currentPlayerIndex)))
                        .collect(Collectors.toList())
        );
    }

    
    
    

    

    
    
}
