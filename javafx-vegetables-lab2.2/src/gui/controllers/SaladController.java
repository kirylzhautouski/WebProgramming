package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import salad.SaladBuilder;
import vegetable.*;

import java.util.ArrayList;

public class SaladController {

    @FXML
    private ComboBox<VegetableState> vegetableStateComboBox;

    @FXML
    private ComboBox<Class<?>> vegetableTypeComboBox;

    @FXML
    private TextField vegetableWeightTextField;

    @FXML
    private Label wrongDataLabel;

    @FXML
    private TextArea vegetablesTextArea;

    @FXML
    private Button createSaladButton;

    @FXML
    private CheckBox sourCreamCheckBox;

    @FXML
    private CheckBox saltCheckBox;

    @FXML
    private CheckBox vegetableOilCheckBox;

    private ArrayList<Vegetable> vegetables = new ArrayList<>();
    private SaladBuilder saladBuilder = new SaladBuilder();

    private VegetableFactory vegetableFactory = new VegetableFactory();

    public void init() {
        vegetableStateComboBox.getItems().setAll(VegetableState.values());
        vegetableStateComboBox.setValue(VegetableState.RAW);

        vegetableTypeComboBox.getItems().setAll(Tomato.class, Carrot.class, Cucumber.class);

        vegetableTypeComboBox.setConverter(new StringConverter<Class<?>>() {
            @Override
            public String toString(Class<?> object) {
                return object.getSimpleName();
            }

            @Override
            public Class<?> fromString(String string) {
                return null;
            }
        });

        vegetableTypeComboBox.setValue(Tomato.class);

        vegetablesTextArea.setWrapText(true);
    }

    @FXML
    private void addVegetable() {
        try {
            vegetables.add(vegetableFactory.getVegetable(vegetableTypeComboBox.getValue(),
                Double.parseDouble(vegetableWeightTextField.getText()), vegetableStateComboBox.getValue()));

            vegetablesTextArea.setText(vegetablesTextArea.getText() + "\n\n" +
                vegetables.get(vegetables.size() - 1));

            wrongDataLabel.setVisible(false);
        } catch (NumberFormatException | NullPointerException ex) {
            wrongDataLabel.setVisible(true);
        }
    }

    @FXML
    private void onCreateSaladButtonClicked() throws Exception {
        SaladBuilder saladBuilder = createSalad();

        if (saladBuilder != null) {
            FXMLLoader chefLoader = new FXMLLoader(getClass().getResource("/gui/views/chef.fxml"));

            Parent root = chefLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Chef");
            stage.setScene(new Scene(root, 600, 300));

            stage.show();

            ChefController chefController = chefLoader.getController();
            chefController.init(saladBuilder);


        }
    }

    @FXML
    private void onDeleteVegetablesButtonCLicked() {
        vegetables.clear();
        vegetablesTextArea.setText("");
    }

    private SaladBuilder createSalad() {
        if (vegetables.size() == 0) {
            wrongDataLabel.setVisible(true);

            return null;
        }

        Vegetable[] vegetablesArray = new Vegetable[vegetables.size()];
        saladBuilder.setVegetables(vegetables.toArray(vegetablesArray));

        if (saltCheckBox.isSelected())
            saladBuilder.setHasSalt();

        if (vegetableOilCheckBox.isSelected())
            saladBuilder.setHasVegetableOil();

        if (sourCreamCheckBox.isSelected())
            saladBuilder.setSourCream();

        return saladBuilder;
    }


}
