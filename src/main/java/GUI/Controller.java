package GUI;



import JSON.JSONReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import org.json.JSONException;

import java.io.IOException;


import javax.swing.*;

public class Controller {
    @FXML
    private ComboBox currencyComboBox;
    @FXML
    private BarChart barChart;
    @FXML
    private ComboBox analysysComboBox;
    @FXML
    private ComboBox periodComboBox;
    @FXML
    private ComboBox analysysValueComboBox;

    @FXML
    private ComboBox groupComboBox;
    @FXML
    private Label currencyLabel;
    @FXML
    private Label periodLabel;
    @FXML
    private Label valueLabel;
    @FXML
    private Label groupLabel;
    @FXML
    private Button showButton;
    @FXML
    private TextField answerTextField;

    private char table;
    private String currency;
    private String period;


    public void initialize() {
        setAnalysysComboBox();
        makeUnVisible();

    }


    public void setAnalysysComboBox() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Wyznaczenie ilości sesji",
                        "Rozkład zmian",
                        "Miary statyczne"
                );
        analysysComboBox.setItems(options);

        analysysComboBox.setOnAction(event-> {
            setAnalysysValueComboBox();
        });
    }


    public void setAnalysysValueComboBox() {
        makeValueComboBoxVisible();
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
        analysysValueComboBox.setOnAction(event -> {
            setCurencyComboBox();
        });


    }


    public void setCurencyComboBox() {
        makeCurencyComboBoxVisible();
        ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "pln",
                            "eur",
                            "usd"
                    );
            currencyComboBox.setItems(options);
            currencyComboBox.setOnAction(event-> {
                setPeriodComboBox();
            });
    }

    public void setPeriodComboBox() {
        makePeriodComboBoxVisible();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Jeden Tydzien",
                        "Dwa Tygodnie",
                        "Jeden Miesiac",
                        "Jeden Kwartal",
                        "Pol Roku",
                        "Jeden Rok"
                );
        periodComboBox.setItems(options);
        periodComboBox.setOnAction(event-> {
            setGroupComboBox();
        });
    }

    private void setGroupComboBox() {
        makeGroupComboBoxVisible();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "A",
                        "B",
                        "C"
                );
        groupComboBox.setItems(options);
        groupComboBox.setOnAction(event -> {
            try {
                showButtonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }


    private void showButtonClicked() throws IOException, JSONException {
        showButton.setVisible(true);
        if(analysysComboBox.getValue().equals("Wyznaczenie ilości sesji")){
            calculateSession();

        }
        if(analysysComboBox.getValue().equals("Miary statyczne")){
            calculateMeasure();
        }
        if(analysysComboBox.getValue().equals("Rozkład zmian")) {
            calculateChange();
        }

        showButton.setOnAction(event -> {showAnswerTextField();});

    }


    private void calculateMeasure() throws IOException, JSONException {
        String tableValue = (String) groupComboBox.getValue();
        table = tableValue.charAt(0);
        currency = (String) currencyComboBox.getValue();
        period = (String) periodComboBox.getValue();
        double sessionAmount = 0;

        if (analysysValueComboBox.getValue().equals("Dominanta")){
            sessionAmount = JSONReader.calculateDominant(table,currency,period);
        }if(analysysValueComboBox.getValue().equals("Mediana")){
            sessionAmount =  JSONReader.calculateMedian(table,currency,period);
        }if(analysysValueComboBox.getValue().equals("Odchylenie standardowe")){
            sessionAmount =  JSONReader.calculateStdDev(table,currency,period);
        }if(analysysValueComboBox.getValue().equals("Współczynnik zmienności")){
            sessionAmount =  JSONReader.calculateVariationCoefficient(table,currency,period);
        }
        answerTextField.setText(String.valueOf(sessionAmount));

    }

    private void showAnswerTextField(){
        answerTextField.setVisible(true);
    }

    private void calculateSession() throws IOException, JSONException {
        String tableValue = (String) groupComboBox.getValue();
        table = tableValue.charAt(0);
        currency = (String) currencyComboBox.getValue();
        period = (String) periodComboBox.getValue();
        int sessionAmount = 0;

        if (analysysValueComboBox.getValue().equals("Wzrostowych")){
            sessionAmount = JSONReader.calculateGrowthSession(table,currency,period);
        }if(analysysValueComboBox.getValue().equals("Spadkowych")){
            sessionAmount =  JSONReader.calculateDownwardSession(table,currency,period);
        }if(analysysValueComboBox.getValue().equals("Bez zmian")){
            sessionAmount =  JSONReader.calculateUnchangedSession(table,currency,period);
        }
        System.out.println(table +" "+currency+" "+period);

        answerTextField.setText(String.valueOf(sessionAmount));
    }


    private void calculateChange() {
    }



    private void makeGroupComboBoxVisible() {
        groupLabel.setVisible(true);
        groupComboBox.setVisible(true);
    }

    public void makeUnVisible(){
        periodComboBox.setVisible(false);
        currencyComboBox.setVisible(false);
        analysysValueComboBox.setVisible(false);
        currencyLabel.setVisible(false);
        periodLabel.setVisible(false);
        valueLabel.setVisible(false);
        groupComboBox.setVisible(false);
        groupLabel.setVisible(false);
        showButton.setVisible(false);
        answerTextField.setVisible(false);


    }

    private void makePeriodComboBoxVisible() {
        periodComboBox.setVisible(true);
        periodLabel.setVisible(true);
    }

    private void makeCurencyComboBoxVisible() {
        currencyComboBox.setVisible(true);
        currencyLabel.setVisible(true);
    }

    private void makeValueComboBoxVisible(){
        analysysValueComboBox.setVisible(true);
        valueLabel.setVisible(true);




    }

}
