package twoknightgame.ui;

import java.io.IOException;
import java.nio.file.Path;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import gameresult.manager.TwoPlayerGameResultManager;
import gameresult.manager.json.JsonTwoPlayerGameResultManager;
import javafx.stage.Stage;
import org.tinylog.Logger;

public class TableViewController {

    private static final int NUMBER_OF_ROWS_TO_SHOW = 15;

    @FXML
    private TableView<TwoPlayerGameResultManager.Wins> tableView;

    @FXML
    private TableColumn<TwoPlayerGameResultManager.Wins, String> playerName;

    @FXML
    private TableColumn<TwoPlayerGameResultManager.Wins, Integer> numberOfWins;
    @FXML
    private Button exitGameButton;

    @FXML
    private void exitGame() {
        Stage leaderboardStage = (Stage) exitGameButton.getScene().getWindow();
        leaderboardStage.close();
        Platform.exit();
        Logger.info("Exiting Game.");
    }

    @FXML
    private Button returnButton;

    @FXML
    private void returnToGame() {
        Stage leaderboardStage = (Stage) returnButton.getScene().getWindow();
        leaderboardStage.close();
        Logger.info("Returning to the Game.");
    }

    @FXML
    private void initialize() throws IOException {
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        numberOfWins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
        ObservableList<TwoPlayerGameResultManager.Wins> observableList = FXCollections.observableArrayList();
        observableList.addAll(new JsonTwoPlayerGameResultManager(Path.of("results.json")).getPlayersWithMostWins(NUMBER_OF_ROWS_TO_SHOW));
        tableView.setItems(observableList);
        tableView.refresh();
        Logger.info("Leaderboard Initialized.");
    }

}
