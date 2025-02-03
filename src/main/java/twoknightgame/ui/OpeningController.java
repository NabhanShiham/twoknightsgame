package twoknightgame.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;


public class OpeningController {

    @FXML
    private TextField player1NameField;

    @FXML
    private TextField player2NameField;

    @FXML
    private Button startGameButton;

    @FXML
    private Button showLeaderBoard;

    @FXML
    private void startGame() throws IOException {
        String player1Name = player1NameField.getText();
        String player2Name = player2NameField.getText();

        Stage stage = (Stage) startGameButton.getScene().getWindow();
        TwoKnightApplication.switchToGameScene(stage, player1Name, player2Name);
        Logger.info("Two Knight Game Displayed!");
    }

    @FXML
    private void showLeaderBoard() throws Exception {
        Stage stage = new Stage();
        Application tableApp = new TableViewApplication();
        tableApp.start(stage);
        Logger.info("Leaderboard Displayed!");

    }
}