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
    private HBox centerbox;
    Game game = new Game();
    ArrayList<ArrayList<String>> playerCards;
    ArrayList<String> gcenter;

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

        game_loop();
    }

    private ImageView createCardImageView(String card) { //create the card image view
        String imagePath = "Image/" + card + ".png";
        Image cardImage = new Image(Main.class.getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(150);
        cardView.setFitWidth(107);
        HBox.setMargin(cardView, new Insets(0, -20, 0, -30));
    
        cardView.setOnMouseClicked((event) -> {
            System.out.println("Player " + (game.get_currentplayer() + 1) + " plays " + card + "!");
            game.gui_player_handle(card);
            if(Game.isValidCard){
                player_cleaner();
                center_cleaner();
                create_gcenter();
                setupPlayerHands();
                while(!Game.gameStarted){
                    Game.gameStarted = true;
                    content.getChildren().clear();
                    player_cleaner();
                    gcenter.clear();
                    playerCards.clear();
                    center_cleaner();
                    game.restart();
                    loop_this();

                }
            }
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
            System.out.println("Player " + (game.get_currentplayer()+1) + " draws a card!");
            game.gui_player_handle("d");
            player_cleaner();
            setupPlayerHands();
        });

        return drawView;
    }

    private void create_first_gcenter() {
        
        game.generateCenter();
        gcenter = game.center;
        
        createGCenterBox(gcenter);
        game.get_firstplayer();
    }

    public void create_gcenter() {
        gcenter = game.center;
        createGCenterBox(gcenter);
    }
    
    private HBox createGCenterBox(ArrayList<String> cunny_card) {
        centerbox = new HBox(-10); // Set spacing between cards
        centerbox.setAlignment(Pos.CENTER);
        
        for (String card : cunny_card) {
            String imagePath = "Image/" + card + ".png";
            Image cardImage = new Image(Main.class.getResourceAsStream(imagePath));
            ImageView cardView = new ImageView(cardImage);
            cardView.setFitHeight(150);
            cardView.setFitWidth(107);
            centerbox.getChildren().add(cardView);
        }
        
        centerbox.setLayoutX(500);
        centerbox.setLayoutY(250);
        
        pane2.getChildren().add(centerbox); // Add centerbox to the pane2
        
        return centerbox;
    }

    public void center_cleaner() {
        pane2.getChildren().remove(centerbox); // Remove the centerbox from the pane2
        centerbox.getChildren().clear(); // Clear the ImageView(s) from the centerbox
    }
    
    private HBox createPlayerHandBox(ArrayList<String> hand) { //create the player hand box
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

    private Button createButton(String buttonText, double layoutX, double layoutY, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setOnAction(event -> {
            pane2.getChildren().clear();
            content.getChildren().clear();
            player_cleaner();
            gcenter.clear();
            game.restart();
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

    //get the current Player card from game
    private void setupPlayerHands() { 
        //game.printGameState();
        ArrayList<String> playerHand = playerCards.get(game.get_currentplayer()); //get the player hand
        HBox playerHandBox = createPlayerHandBox(playerHand); //create the player hand box
        playerHandBoxes.add(playerHandBox);                  //add the player hand box to the arraylist  
        content.getChildren().add(playerHandBoxes.get(0));
    }

    public void cleaner(){
        pane2.getChildren().clear();
        content.getChildren().clear();
        game.restart();
    }

    public void player_cleaner(){
        playerHandBoxes.clear(); // clear the player hand box
        content.getChildren().clear();
    }


    public void game_loop(){
        game.initializeGame();
        create_first_gcenter();
        game.current_determine();
        playerCards = game.getPlayerCards();
        gcenter = game.center;
        setupPlayerHands();
        pane2.getChildren().add(createdrawView());
        content.getChildren().add(1, showPlayerTurn(game.get_currentplayer()));

    }

    public void loop_this(){
        game.initializeGame();
        playerCards = game.getPlayerCards();
        gcenter = game.center;
        setupPlayerHands();
        content.getChildren().add(1, showPlayerTurn(game.get_currentplayer()));

    }
    // private void handleCardClick() {
    //     HBox currentPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
    //     currentPlayerHandBox.getChildren().clear();
    //     currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    //     HBox nextPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
    //     content.getChildren().set(0, nextPlayerHandBox);
    
    //     Text currentPlayerText = showPlayerTurn(currentPlayerIndex);
    //     content.getChildren().set(1, currentPlayerText);
    
    //     if (currentPlayerIndex == 0) {
    //         currentPlayerIndex = 0;
    //         HBox firstPlayerHandBox = playerHandBoxes.get(currentPlayerIndex);
    //         content.getChildren().set(0, firstPlayerHandBox);
    //     }
    
    //     ArrayList<String> currentPlayerCards = playerCards.get(currentPlayerIndex);
    //     currentPlayerHandBox.getChildren().addAll(
    //             currentPlayerCards.stream()
    //                     .map(this::createCardImageView)
    //                     .collect(Collectors.toList())
    //     );
    // }
    
   
}
