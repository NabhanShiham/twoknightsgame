package twoknightgame.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class TwoKnightApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/opening.fxml"));
        Parent root = loader.load();
        stage.setTitle("TwoKnight Chess Game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        Logger.info("Opening Scene Displayed!");
    }

    public static void switchToGameScene(Stage stage, String player1Name, String player2Name) throws IOException {
        FXMLLoader loader = new FXMLLoader(TwoKnightApplication.class.getResource("/ui.fxml"));
        TwoKnightController gameController = new TwoKnightController();

        if (player1Name == null || player1Name.isEmpty()) {
            player1Name = "Player 1";
        }
        if (player2Name == null || player2Name.isEmpty()) {
            player2Name = "Player 2";
        }
        gameController.setPlayer1Name(player1Name);
        gameController.setPlayer2Name(player2Name);
        Logger.info("Player Names Set: "+player1Name+ " & " + player2Name);
        loader.setController(gameController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setX(420);
        stage.setY(70);
        Logger.info("Switching to Game Scene...");
    }
}