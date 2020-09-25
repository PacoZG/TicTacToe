package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;

public class TicTacToeApplication extends Application {
    //private String [] Symbols = {"X", "O"};
    private String symbol;
    private ArrayList<Button> buttons;
    private TicTacToeGame game;
    private Label labelTop;
    private Button playButton;
    private int counter;

    @Override
    public void start(Stage stage) {

        // Random turn = new Random(); // Starting with a random turn
        // symbol = Symbols[turn.nextInt(2)];
        symbol = "X";
        ///// Starting local variables
        buttons = new ArrayList<>();
        game = new TicTacToeGame(this.symbol);
        playButton = new Button("Playing");
        playButton.setDisable(true);
        playButton.setAlignment(Pos.CENTER);
        counter = 0;
        // creating top label
        labelTop = new Label("Turn: " + this.symbol);
        labelTop.setFont(Font.font("Monospaced", 25));
        labelTop.setAlignment(Pos.CENTER);

        // creating border
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10, 10, 10, 10));

        // creating grid and buttons
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // creating buttons, store in an ArrayList and adding to the grid
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                Button btn = new Button(" ");
                btn.setFont(Font.font("Monospaced", 40));
                buttons.add(btn);
                int index = buttons.indexOf(btn);
                btn.setOnAction((event) -> {
                    makePlay(index);
                });
                grid.add(btn, j, i);
            }
        }
        // Setting action for the "play again" game button
        playButton.setOnAction((event) -> {
            start(stage);
        });

        // adding label and grid to the bPane
        layout.setTop(labelTop);
        layout.setCenter(grid);
        layout.setBottom(playButton);
        Scene scene = new Scene(layout, 300, 365);
        stage.setScene(scene);
        stage.show();
    }

    public void makePlay(int index) {
        if (buttons.get(index).getText().equals(" ")) {
            buttons.get(index).setText(this.symbol);
            game.updateList(buttons);

            if (counter == 8 && !game.checkIfWinner(symbol)) { // checking if all the buttons have been pressed
                playButton.setDisable(false);
                buttons.forEach((button) -> {
                    button.setDisable(true);
                });
                labelTop.setText("The end!");
                playButton.setText("Play again");
            } else if (game.checkIfWinner(symbol)) { // Checking if somebody wins
                // labelTop.setText(symbol + " Wins!");
                labelTop.setText("The end!");
                playButton.setText("Play again");
                playButton.setDisable(false);
                buttons.forEach((button) -> {
                    button.setDisable(true);
                });
            } else {
                this.symbol = game.changeTurn();
                labelTop.setText("Turn: " + this.symbol);
                counter++;
            }
        }
    }
}

class App {
    public static void main(String[] args) {
        Application.launch(TicTacToeApplication.class);
    }
}