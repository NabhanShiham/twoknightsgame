package twoknightgame.ui;

import game.State;
import gameresult.TwoPlayerGameResult;
import gameresult.manager.json.JsonTwoPlayerGameResultManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.SneakyThrows;
import org.tinylog.Logger;
import twoknightgame.model.TwoKnightModel;
import twoknightgame.model.Position;
import twoknightgame.model.Square;

import twoknightgame.util.TwoKnightMoveSelector;



import java.io.*;

import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class TwoKnightController{

    @FXML
    private GridPane board;
    @FXML
    private Label playerTurnLabel;
    @FXML
    private Label gameDurationLabel;

    @FXML
    private Label numberOfMovesLabel;
    @Setter
    private static String player1Name;
    @Setter
    private static String player2Name;
    private static TwoKnightModel model = new TwoKnightModel();
    private State.Player currentPlayer = model.getCurrentPlayer();
    private TwoKnightMoveSelector selector = new TwoKnightMoveSelector(model);
    private static int numberOfTurns = 0;
    private static Temporal startTime;
    private javafx.util.Duration gameDuration = javafx.util.Duration.ZERO;
    private Timeline gameTimer;



    @FXML
    private void initialize() {
        startTime = LocalDateTime.now();
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
        playerTurnLabel.setText(player1Name + "'s turn!");
        gameTimer = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            gameDuration = gameDuration.add(javafx.util.Duration.seconds(1));
            gameDurationLabel.setText("Time: " + formatDuration(gameDuration));
        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();
        numberOfMovesLabel.setText("Moves: " + numberOfTurns);
        Logger.info("Board Initialized.");
    }

    @SneakyThrows
    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.setPrefSize(88, 88);
        square.setPadding(new Insets(0, 0, 0, 0));
        GridPane.setMargin(square, new Insets(0, 0, 0, 0));
        square.getStyleClass().add((i + j) % 2 == 0 ? "square-light" : "square-dark");


        Image whiteKnightImage = new Image("/whiteKnight.png");
        Image blackKnightImage = new Image("/blackKnight.png");

        var piece = new ImageView();
        piece.setFitWidth(80);
        piece.setFitHeight(80);

        piece.imageProperty().bind(createSquareBinding(model.squareProperty(i, j), whiteKnightImage, blackKnightImage));

        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        try {
            var square = (StackPane) mouseEvent.getSource();
            var row = GridPane.getRowIndex(square);
            var col = GridPane.getColumnIndex(square);
            Position position = new Position(row, col);

            switch (selector.getPhase()) {
                case SELECT_FROM:
                    Square selectedPiece = model.getSquare(position);
                    if ((currentPlayer == State.Player.PLAYER_1 && selectedPiece != Square.WHITEKNIGHT) ||
                            (currentPlayer == State.Player.PLAYER_2 && selectedPiece != Square.BLACKKNIGHT)) {
                        Logger.error("Invalid Selection!");
                        return;
                    }
                    selector.select(position);
                    Logger.info("From: ("+position.row() + "," + position.col() + ")");
                    if (selector.isInvalidSelection()) {
                        Logger.error("Invalid Selection!");
                        if (model.isGameOver()) {
                            handleGameOver();
                        }
                    } else {
                        showSelection(position);
                    }
                    break;

                case SELECT_TO:
                    selector.select(position);
                    if (selector.isInvalidSelection()) {
                        Logger.error("Invalid Selection!");
                        return;
                    } else {
                        model.makeMove(selector.getFrom(), position);
                        Logger.info("To: ("+position.row() + "," + position.col() + ")");
                        numberOfTurns++;
                        numberOfMovesLabel.setText("Moves: " + numberOfTurns);
                        ImageView forbiddenPositionImage = new ImageView("/forbiddenPosition.png");
                        forbiddenPositionImage.setFitWidth(50);
                        forbiddenPositionImage.setFitHeight(50);

                        for (Position pos : model.getForbiddenpositions()) {
                            if (model.getSquare(pos) == Square.EMPTY) {
                                StackPane square1 = getSquare(pos);
                                ImageView cloneImage = new ImageView(forbiddenPositionImage.getImage());
                                cloneImage.setFitWidth(forbiddenPositionImage.getFitWidth());
                                cloneImage.setFitHeight(forbiddenPositionImage.getFitHeight());
                                square1.getChildren().add(cloneImage);
                            }
                        }
                        switchPlayer();
                        selector.reset();
                        clearHighlights();
                        Logger.info("Successful Move Completed!");
                        if (model.isGameOver()) {
                            handleGameOver();
                        }
                    }
                    break;

                case READY_TO_MOVE:
                    selector.select(position);
                    if (model.isGameOver()) {
                        handleGameOver();
                    }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void handleGameOver() throws IOException {
        Logger.info("Game Over Condition Reached.");
        gameTimer.stop();
        String winner = model.getStatus() == State.Status.PLAYER_1_WINS ? player1Name : player2Name;
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! " + winner + " is the Winner!");
        alert.showAndWait();
        Logger.info(winner + " is the winner!");

        TwoPlayerGameResult result = createGameResult();
        addResultToJSON(result);

        Platform.runLater(() -> {
            try {
                new TableViewApplication().start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private void switchPlayer() {
        currentPlayer = model.getCurrentPlayer();
        String currentPlayerName = (currentPlayer == State.Player.PLAYER_1) ? player1Name : player2Name;
        playerTurnLabel.setText(currentPlayerName + "'s turn!");
        Logger.info("Current Player: "+ currentPlayerName);
    }

    private ObjectBinding<Image> createSquareBinding(ReadOnlyObjectProperty<Square> squareProperty, Image whiteKnightImage, Image blackKnightImage) {
        return new ObjectBinding<Image>() {
            {
                super.bind(squareProperty);
            }
            @Override
            protected Image computeValue() {
                return switch (squareProperty.get()) {
                    case EMPTY -> null;
                    case WHITEKNIGHT -> whiteKnightImage;
                    case BLACKKNIGHT -> blackKnightImage;
                };
            }
        };
    }

    private static TwoPlayerGameResult createGameResult(){
        try {
            Duration duration = Duration.between(startTime, LocalDateTime.now());
            return TwoPlayerGameResult.builder()
                    .player1Name(player1Name)
                    .player2Name(player2Name)
                    .status(model.getStatus())
                    .numberOfTurns(numberOfTurns)
                    .duration(duration)
                    .created(ZonedDateTime.now())
                    .build();
        } catch (Exception e) {
            Logger.error("Exception Occurred in creating the Game Result:"+e);
            e.printStackTrace();
            throw e;
        }
    }

    private void addResultToJSON(TwoPlayerGameResult result) {
        JsonTwoPlayerGameResultManager manager = new JsonTwoPlayerGameResultManager(Path.of("results.json"));
        try {
            manager.add(result);
            Logger.info("Saved Results to JSON!");
        } catch (IOException e) {
            Logger.error("Could not save results to JSON! Error: "+e);
        }
    }

    private List<Position> getPossibleMoves(Position position) {
        List<Position> possibleMoves = new ArrayList<>();
        int[][] offsets = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        for (int[] offset : offsets) {
            int newRow = position.row() + offset[0];
            int newCol = position.col() + offset[1];
            if (newRow >= 0 && newRow < board.getRowCount() && newCol >= 0 && newCol < board.getColumnCount()) {
                possibleMoves.add(new Position(newRow, newCol));
            }
        }
        return possibleMoves;
    }
    private void showSelection(Position position) {
        clearHighlights();
        var square = getSquare(position);
        square.getStyleClass().add("selected");
        for (Position move : getPossibleMoves(position)) {
            if (!model.getForbiddenpositions().contains(move)) {
                getSquare(move).getStyleClass().add("highlighted");
            }
        }
    }
    private void clearHighlights() {
        for (var child : board.getChildren()) {
            var square = (StackPane) child;
            square.getStyleClass().removeAll("selected", "highlighted");
        }
    }
    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }
    private String formatDuration(javafx.util.Duration duration) {
        long seconds = (long) duration.toSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


}