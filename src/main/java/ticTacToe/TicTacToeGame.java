package ticTacToe;

import java.util.ArrayList;
import javafx.scene.control.Button;

public class TicTacToeGame {

    private ArrayList<Button> ButtonsList;
    private String Symbol;

    public TicTacToeGame(String symbol) {
        this.ButtonsList = new ArrayList<>();
        this.Symbol = symbol;
    }

    public String changeTurn() {
        if (this.Symbol.equals("X")) {
            this.Symbol = "O";
        } else {
            this.Symbol = "X";
        }
        return this.Symbol;
    }

    public void updateList(ArrayList buttonList){
        this.ButtonsList = buttonList;
    }

    public boolean checkIfWinner(String symbol) {
        int numOfEquals = 0;
        // checking  rows
        for (int i = 0; i <=2; i++){
            for(int j = 0; j <= 2; j++){
                if (ButtonsList.get((3*i)+j).getText().equals(symbol)){
                    numOfEquals++;
                }
            }
            if (numOfEquals == 3){
                return true;
            }
            numOfEquals = 0;
        }
        // checking columns
        for (int i = 0; i <=2; i++){
            for(int j = 0; j <= 2; j++){
                if (ButtonsList.get((3*j)+i).getText().equals(symbol)){
                    numOfEquals++;
                }
            }
            if (numOfEquals == 3){
                return true;
            }
            numOfEquals = 0;
        }
        // checking diagonals
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i <=2; i++){
            if (ButtonsList.get((4*i)).getText().equals(symbol)){
                diag1++;
            }
            if (ButtonsList.get((2*i+2)).getText().equals(symbol)){
                diag2++;
            }
        }
        return diag1 == 3 || diag2 == 3;
    }
}
