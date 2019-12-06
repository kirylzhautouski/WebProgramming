package gui.controllers;

import chef.Chef;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import salad.SaladBuilder;
import vegetable.Vegetable;
import vegetable.VegetableParameters;
import vegetable.VegetableState;

import java.util.ArrayList;

public class ChefController {

    @FXML
    private TextArea vegetablesTextArea;

    @FXML
    private Label wrongParametersLabel;

    @FXML
    private ComboBox<VegetableState> vegetableStateComboBox;

    @FXML
    private TextField caloriesTextField;

    private Chef chef;

    public void init(SaladBuilder saladBuilder) {
        this.chef = new Chef(saladBuilder);

        vegetablesTextArea.setWrapText(true);

        vegetableStateComboBox.getItems().setAll(VegetableState.values());
        vegetableStateComboBox.setValue(VegetableState.RAW);
    }

    @FXML
    private void onFindVegetablesClick() {
        try {
            double calories = Double.parseDouble(caloriesTextField.getText());

            if (calories > 0) {
                VegetableState state = vegetableStateComboBox.getValue();

                VegetableParameters vegetableParameters = new VegetableParameters(calories, state);
                ArrayList<Vegetable> result = chef.findVegetablesWithParameters(vegetableParameters);

                if (result.isEmpty()) {
                    vegetablesTextArea.setText("No vegetables found");
                }
                else {
                    vegetablesTextArea.setText(result.toString());
                }
            } else {
                showError();
            }

            wrongParametersLabel.setVisible(false);
        } catch (NumberFormatException | NullPointerException ex) {
            showError();
        }
    }

    @FXML
    private void onSortByCaloriesClick() {
        vegetablesTextArea.setText(chef.sortedByCalories().toString());
    }

    @FXML
    private void onCountAllCaloriesClick() {
        vegetablesTextArea.setText("Number of all calories: "  + chef.countAllCalories());
    }

    private void showError() {
        wrongParametersLabel.setVisible(true);
    }

}
