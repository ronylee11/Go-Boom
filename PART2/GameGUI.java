import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameGUI extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox content;
    private Pane pane2 = new Pane();
    private Pane scoreboard = new Pane();
    private Stage stage;
    private Scene mainMenuScene;
    private List<HBox> playerHandBoxes;
    private HBox centerbox;
    Game game = new Game();
    Deck decks = new Deck();
    ArrayList<ArrayList<String>> playerCards;
    ArrayList<String> gcenter;
    int[] totalScore = new int[4];
    private Text[] playerScores;

    public GameGUI(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        this.mainMenuScene = mainMenuScene;
        this.playerHandBoxes = new ArrayList<>();

        content = new VBox(10); // Set spacing between cards
        StackPane stackPane = new StackPane();
        scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        //scrollPane.setMaxHeight(240);
        scrollPane.setStyle("-fx-border-color: black");
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        //scrollPane.setBackground(new Background(backgroundFill));

        // Create scrollboard pane
        scoreboard.setPrefWidth(500);
        scoreboard.setPrefHeight(100);
        scoreboard.setBackground(new Background(backgroundFill));
        scoreboard.setStyle("-fx-border-color: black");

        Button returnBtn = createButton("Return", 14, 28, 130, 60);
        Button button2 = saveButton("Save", 920, 28, 130, 60);

        pane2.getChildren().addAll(returnBtn, button2);
        getChildren().addAll(scoreboard, scrollPane, pane2);

        // Anchor the scrollPane to the bottom of the AnchorPane
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(scrollPane, 50.0);
        AnchorPane.setRightAnchor(scrollPane, 50.0);

        // Anchor the pane2 to the top-right of the AnchorPane
        AnchorPane.setTopAnchor(pane2, 10.0);
        AnchorPane.setRightAnchor(pane2, 10.0);

        // Anchor the scoreboard to the top and center of the AnchorPane
        AnchorPane.setTopAnchor(scoreboard, 10.0);
        AnchorPane.setLeftAnchor(scoreboard, 200.0);
        AnchorPane.setRightAnchor(scoreboard, 200.0);

        // Bind the size of the content VBox to the width of the scrollPane
        content.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));

        // Scroll to the bottom of the ScrollPane when the content changes
        scrollPane.vvalueProperty().bind(content.heightProperty());

        // Scroll to the bottom of the ScrollPane when the content changes
        AnchorPane.setTopAnchor(stackPane, 10.0);
        
        Image image = new Image(Main.class.getResourceAsStream("Image/Go Boom Background2.png"));
        BackgroundImage bImage = new BackgroundImage(image, null, null, null, null);
        Background bg = new Background(bImage);
        content.setBackground(bg);
        
        // Create the initial game setup
        if(!Game.gameStarted){
            game_loop();
        }else{
            load_game_loop(); //this line is free from captain buggy
        }
    }
    
    // Create an ImageView for a card
    private ImageView createCardImageView(String card) { //create the card image view
        String imagePath = "Image/" + card + ".png";
        Image cardImage = new Image(Main.class.getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(150);
        cardView.setFitWidth(107);
        HBox.setMargin(cardView, new Insets(30, -20, 0, -30));
        // Handle mouse click event on a card
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
            if(decks.isEmpty()){
                pane2.getChildren().remove(drawView);
            }
            player_cleaner();
            setupPlayerHands();
        });

        return drawView;
    }
    // Update the scoreboard
    public void updateScoreboard() {
        // Clear the scoreboard pane
        scoreboard.getChildren().clear();

        // Create and position the "Scoreboard" text
        Text scoreLabel = new Text("Scoreboard");
        Font scoreFont = Font.loadFont(getClass().getResourceAsStream("Font/Aleo-Bold.otf"), 42);
        Font playerScoreFont = Font.loadFont(getClass().getResourceAsStream("Font/Aleo-Bold.otf"), 24);
        scoreLabel.setFill(Color.BLACK);
        scoreLabel.setFont(scoreFont);
        scoreLabel.setTranslateY(37);
        scoreLabel.setTranslateX(218);
        scoreboard.getChildren().add(scoreLabel);

        // Create and position the player score texts
        for (int i = 0; i < playerScores.length; i++) {
            Text playerScore = playerScores[i];
            playerScore.setFill(Color.BLACK);
            playerScore.setFont(playerScoreFont);
            playerScore.setTranslateY(80);
            playerScore.setTranslateX(25 + 170 * i);
            scoreboard.getChildren().add(playerScore);
        }
    }

    // Update the player scores
    public void updateScores() {
        // Update player scores in the playerScores array
        for (int i = 0; i < totalScore.length; i++) {
            totalScore[i] = totalScore[i] + game.getPlayerScore(i); //get the player score
            int j = i + 1;
            playerScores[i].setText(String.format("Player %d: %d", j, totalScore[i]));
        }

        // Update the scoreboard display
        updateScoreboard();
    }

    // Create the initial gcenter (centerbox) with cards
    private void create_first_gcenter() {
        
        game.generateCenter();
        gcenter = game.center;
        
        createGCenterBox(gcenter);
        game.get_firstplayer();
    }
    // Create the gcenter (centerbox) with cards
    public void create_gcenter() {
        gcenter = game.center;
        createGCenterBox(gcenter);
    }
    // Create the centerbox and add cards to it
    private HBox createGCenterBox(ArrayList<String> cunny_card) {
        centerbox = new HBox(-30); // Set spacing between cards
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
    // Clear the centerbox and remove it from pane2
    public void center_cleaner() {
        pane2.getChildren().remove(centerbox); // Remove the centerbox from the pane2
        centerbox.getChildren().clear(); // Clear the ImageView(s) from the centerbox
    }
    // Create the player hand box and add cards to it
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
        Font font = Font.loadFont(getClass().getResourceAsStream("Font/2156-font.otf"), 24);
        button.setFont(font);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setStyle("-fx-background-color: #DDDDDD; -fx-border-color: black;");
        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
            button.setStyle("-fx-background-color: #EEEEEE; -fx-border-color: black;");
        });
        button.setOnMouseExited(event -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            button.setStyle("-fx-background-color: #DDDDDD; -fx-border-color: black;");
        });
        button.setOnAction(event -> {
            pane2.getChildren().clear();
            content.getChildren().clear();
            scoreboard.getChildren().clear();
            player_cleaner();
            gcenter.clear();
            game.restart();
            stage.setScene(mainMenuScene); // need a way to quit the game after 
                                            // clicking the return button
        });
        return button;
    }
    private Button saveButton(String buttonText, double layoutX, double layoutY, double minWidth, double minHeight) {
        Button button = new Button(buttonText);
        Font font = Font.loadFont(getClass().getResourceAsStream("Font/2156-font.otf"), 24);
        //button.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");
        button.setFont(font);;
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(minWidth, minHeight);
        button.setStyle("-fx-background-color: #DDDDDD; -fx-border-color: black;");
        button.setOnAction(event -> {
            game.gui_player_handle("s");
            Platform.exit();
        });
        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
            button.setStyle("-fx-background-color: #EEEEEE; -fx-border-color: black;");
        });
        button.setOnMouseExited(event -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            button.setStyle("-fx-background-color: #DDDDDD; -fx-border-color: black;");
        });
        return button;
    }
    // Show the current player's turn as text
    private HBox showPlayerTurn(int currentPlayerIndex) {
        Font playerFont = Font.loadFont(getClass().getResourceAsStream("Font/2156-font.otf"), 20);
        Text playerText = new Text("Player " + (currentPlayerIndex + 1));
        HBox playerTurnBox = new HBox(0.0,playerText);
        playerTurnBox.setMaxWidth(80);
        playerTurnBox.setPrefHeight(25);
        playerTurnBox.setStyle("-fx-background-color: #FFFFFF;");
        playerText.setFont(playerFont);
        return playerTurnBox;
    }

    //need rearrangment of the HBoxes
    public HBox displayTrickNumber(int trickNumber) {
        Font TrickFont = Font.loadFont(getClass().getResourceAsStream("Font/2156-font.otf"), 20);
        Text trickText = new Text("Trick #" + game.getTrickNumber());
        HBox trickBox = new HBox(0.0, trickText);
        trickBox.setStyle("-fx-background-color: #FFFFFF;");
        trickBox.setMaxHeight(25);
        trickBox.setMaxWidth(80);
        trickText.setFont(TrickFont);
        trickBox.setLayoutX(100);
        return trickBox;
    }

    //get the current Player card from game
    private void setupPlayerHands() { 
        //game.printGameState();
        ArrayList<String> playerHand = playerCards.get(game.get_currentplayer()); //get the player hand
        HBox playerHandBox = createPlayerHandBox(playerHand); //create the player hand box
        playerHandBoxes.add(playerHandBox);                  //add the player hand box to the arraylist  
        content.getChildren().add(playerHandBoxes.get(0));
        content.getChildren().add(1, showPlayerTurn(game.get_currentplayer()));
        content.getChildren().add(displayTrickNumber(game.getTrickNumber()));
    }
    // Clear the content and player hand boxes
    public void cleaner(){
        pane2.getChildren().clear();
        content.getChildren().clear();
        game.restart();
    }
    // Clear the player hand boxes and content
    public void player_cleaner(){
        playerHandBoxes.clear(); // clear the player hand box
        content.getChildren().clear();
    }

    // Main game loop
    public void game_loop(){
        // Initialize playerScores array
        playerScores = new Text[4];
        for (int i = 0; i < playerScores.length; i++) {
            playerScores[i] = new Text();
        }
        game.initializeGame();
        create_first_gcenter();
        game.current_determine();
        playerCards = game.getPlayerCards();
        gcenter = game.center;
        setupPlayerHands();
        pane2.getChildren().add(createdrawView());
        updateScores();
    }
    public void load_game_loop(){
        playerScores = new Text[4];
        for (int i = 0; i < playerScores.length; i++) {
            playerScores[i] = new Text();
        }
        game.gui_load();
        updateScores();
        playerCards = game.getPlayerCards();
        gcenter = game.center;
        if(gcenter != null){
            createGCenterBox(gcenter);
        }
        setupPlayerHands();
        pane2.getChildren().add(createdrawView());
        updateScores();
    }

    // Restart the game loop
    public void loop_this(){ //might have some error from time to time
        updateScores();
        game.initializeGame();
        playerCards = game.getPlayerCards();
        gcenter = game.center;
        setupPlayerHands();
        pane2.getChildren().add(createdrawView());
    }



    
}
