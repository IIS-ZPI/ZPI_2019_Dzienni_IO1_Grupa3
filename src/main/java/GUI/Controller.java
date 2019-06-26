package GUI;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;

import javax.swing.*;

public class Controller {
    @FXML
    private ComboBox curencyComboBox;
    @FXML
    private BarChart barChart;
    @FXML
    private ComboBox analysysComboBox;
    @FXML
    private ComboBox periodComboBox;
    @FXML
    private ComboBox analysysValueComboBox;

    @FXML
    public void initialize() {
        setPeriodComboBox();
        setCurencyComboBox();
        setAnalysysComboBox();

    }

    public void setPeriodComboBox() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Tydzien",
                        "2 tygodnie",
                        "Miesiąc",
                        "Pól roku",
                        "Rok"
                );
        periodComboBox.setItems(options);
    }

    public void setCurencyComboBox() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "PLN",
                        "EUR",
                        "USD"
                );
        curencyComboBox.setItems(options);
    }


    public void setAnalysysComboBox() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Wyznaczenie ilości sesji",
                        "Rozkład zmian",
                        "Miary statyczne"
                );
        analysysComboBox.setItems(options);
        analysysComboBox.setOnAction(e -> setAnalysysValueComboBox());


    }

    @FXML
    public void setAnalysysValueComboBox() {
        ObservableList<String> options =
                FXCollections.observableArrayList();
        if (analysysComboBox.getValue().equals("Wyznaczenie ilości sesji")) {
            options.setAll("Wzrostowych", "Spadkowych", "Bez zmian");
        }
        if (analysysComboBox.getValue().equals("Rozkład zmian")) {
            options.setAll("Dominanta", "Mediana", "Odchylenie standardowe", "Współczynnik zmienności");
        }
        if (analysysComboBox.getValue().equals("Miary statyczne")) {
            options.setAll("Miesięcznych", "Kwartalnych");
        }
        analysysValueComboBox.setItems(options);

    }
}
