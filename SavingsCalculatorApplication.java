package application;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SavingsCalculatorApplication extends Application{

    @Override
    public void start (Stage stage){

        BorderPane bp1 = new BorderPane (); // Main BorderPane
        bp1.setPadding(new Insets(20, 20, 20, 20));


        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Savings Counter");


        VBox vBox = new VBox();// VBox on the top of the main BorderPane
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        // BorderPanes inside the VBox
        BorderPane bpTop = new BorderPane ();
        BorderPane bpBottom = new BorderPane ();

        // Defining slider 1, labels related and behavior
        Label label1 = new Label ("Monthly savings");

        Label label2 = new Label("25");
        Slider savingsAmount = new Slider(25, 250, 25);
        savingsAmount.setShowTickLabels(true);
        savingsAmount.setShowTickMarks(true);
        savingsAmount.setBlockIncrement(25);
        savingsAmount.setMinorTickCount(25);
        savingsAmount.setMajorTickUnit(50);

        // Defining slider 2, labels related and behavior
        Label label3 = new Label ("Yearly interest rate");

        Label label4 = new Label("0");
        Slider interestRate = new Slider(0, 10, 0.5);
        interestRate.setShowTickLabels(true);
        interestRate.setShowTickMarks(true);
        interestRate.adjustValue(0);
        interestRate.setBlockIncrement(5);
        interestRate.setMinorTickCount(5);
        interestRate.setMajorTickUnit(5);

        XYChart.Series saving = new XYChart.Series();
        XYChart.Series withInterest = new XYChart.Series();

        DecimalFormat df2 = new DecimalFormat("0.00");
        // Using the amount slider
        savingsAmount.setOnMouseReleased((event) -> {
            label2.setText(df2.format(savingsAmount.getValue()));
            sliderAction (saving, withInterest, savingsAmount, interestRate);
            saving.setName("Amount: " + (double)Math.round(savingsAmount.getValue()) + "€");
        });

        // Using the interest slider
        interestRate.setOnMouseReleased((event) -> {
            label4.setText((double)Math.round(interestRate.getValue()) + "");
            sliderAction (saving, withInterest, savingsAmount, interestRate);
            withInterest.setName("Interest: " + (double)Math.round(interestRate.getValue()) + "%");

        });

        // Adding the lines to the chart
        lineChart.getData().add(saving);
        lineChart.getData().add(withInterest);


        // Inserting components in their positions
        // Top Border Pane
        bpTop.setLeft(label1);
        bpTop.setCenter(savingsAmount);
        bpTop.setRight(label2);
        // Bottom border Pane
        bpBottom.setLeft(label3);
        bpBottom.setCenter(interestRate);
        bpBottom.setRight(label4);
        // Vertical box
        vBox.getChildren().add(bpTop);
        vBox.getChildren().add(bpBottom);

        // Main Border Pane
        bp1.setTop(vBox);
        bp1.setCenter(lineChart);

        Scene view = new Scene(bp1, 640, 480);
        stage.setScene(view);
        stage.show();
    }

    // Operations and functionality of the sliders are done in the code below
    public void sliderAction(XYChart.Series totalSaving, XYChart.Series wInterest, Slider savings, Slider interests){
        totalSaving.getData().clear();
        wInterest.getData().clear();

        double amount = savings.getValue();
        double interest = interests.getValue();
        double yearlySaving = 0;
        double savingWInt = 0;

        for (int i = 0; i <= 30; i++){
            totalSaving.getData().add(new XYChart.Data(i, yearlySaving));
            yearlySaving += 12 * amount;
        }

        for (int i = 0; i <= 30; i++){
            wInterest.getData().add(new XYChart.Data(i, savingWInt));
            savingWInt = (savingWInt + (12*amount))*(1+interest/100);
        }
    }
}

class App {
    public static void main(String[] args) {
        Application.launch(SavingsCalculatorApplication.class);
    }
}