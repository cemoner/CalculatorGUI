package sample;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button2;

    @FXML
    private Button button3;


    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button buttonAddition;

    @FXML
    private Button buttonDecimalPoint;

    @FXML
    private Button buttonMultiplication;

    @FXML
    private TextField textField;

    @FXML
    private Button buttonSubtraction;


    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button buttonDivision;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonResult;

    @FXML
    private Button buttonClear;

    Character[] validates = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '.', ')', '('};
    char[] operations = {'+', '-', '*', '/', ')','('};
    List<Integer> indexesOfOperations = new ArrayList<>();
    List<String> separated = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addingListeners();
        buttonSettings();

    }

    public void addingListeners() {
        textField.textProperty().addListener((v, oldValue, newValue) -> {
            for (int i = 0; i <= textField.getText().length() - 1; i++) {
                boolean isValid = false;
                for (Character valid : validates) {
                    if (newValue.charAt(i) == valid) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    textField.setText(oldValue);
                }
            }

        });
        textField.textProperty().addListener((v, oldValue, newValue) -> {
            for (int m = 0; m <= textField.getText().length() - 2; m++) {
                boolean isOperation = false;
                for (int i = 10; i <= 16; i++) {
                    try {
                        char character = newValue.charAt(m);
                        if (character == validates[i]) {
                            isOperation = true;
                            break;
                        }
                    } catch (Exception e) {
                    }

                }
                if (isOperation) {
                    isOperation = false;
                    for (int i = 10; i <= 14; i++) {
                        try {
                            if (newValue.charAt(m + 1) == validates[i]) {
                                isOperation = true;
                                break;
                            }
                        } catch (Exception e) {

                        }
                    }
                    if (isOperation) {
                        textField.setText(oldValue);
                    }
                }
            }

        });
        textField.textProperty().addListener((v, oldValue, newValue) -> {
            if (textField.getText().length() >= 1) {
                for (int i = 10; i <= 15; i++) {
                    try {
                        if (textField.getText().charAt(0) == validates[i]) {
                            textField.setText(oldValue);
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });

    }

    public void buttonSettings() {
        button0.setOnAction(e -> textField.setText(textField.getText() + '0'));
        button1.setOnAction(e -> textField.setText(textField.getText() + '1'));
        button2.setOnAction(e -> textField.setText(textField.getText() + '2'));
        button3.setOnAction(e -> textField.setText(textField.getText() + '3'));
        button4.setOnAction(e -> textField.setText(textField.getText() + '4'));
        button5.setOnAction(e -> textField.setText(textField.getText() + '5'));
        button6.setOnAction(e -> textField.setText(textField.getText() + '6'));
        button7.setOnAction(e -> textField.setText(textField.getText() + '7'));
        button8.setOnAction(e -> textField.setText(textField.getText() + '8'));
        button9.setOnAction(e -> textField.setText(textField.getText() + '9'));
        buttonAddition.setOnAction(e -> textField.setText(textField.getText() + '+'));
        buttonSubtraction.setOnAction(e -> textField.setText(textField.getText() + '-'));
        buttonMultiplication.setOnAction(e -> textField.setText(textField.getText() + '*'));
        buttonDivision.setOnAction(e -> textField.setText(textField.getText() + '/'));
        buttonDecimalPoint.setOnAction(e -> textField.setText(textField.getText() + '.'));
        buttonDelete.setOnAction(e -> {
            try {
                textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
            } catch (StringIndexOutOfBoundsException m) {

            }
        });
        buttonResult.setOnAction(e -> findResult());
        buttonClear.setOnAction(e -> textField.setText(""));
    }

    /* Form a string array and break it down according to primary operation and remember */

    public void findResult() {
        settingUpArrayList();
        separatorString();
        forMultiplication(separated);
        forDivision(separated);
        forAddition(separated);
        forSubtraction(separated);
        textField.setText(separated.get(0));
        separated.remove(0);

    }

    public void forMultiplication(List<String> list) {
        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).equals("*")) {
                double result = Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1));
                list.set(i - 1, Double.toString(result));
                list.remove(i);
                list.remove(i);
                i = 0;
            }
        }
    }

    public void forDivision(List<String> list) {
        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).equals("/")) {
                double result = Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1));
                list.set(i - 1, Double.toString(result));
                list.remove(i);
                list.remove(i);
                i = 0;
            }
        }
    }

    public void forAddition(List<String> list) {
        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).equals("+")) {
                double result = Double.parseDouble(list.get(i - 1)) + Double.parseDouble(list.get(i + 1));
                list.set(i - 1, Double.toString(result));
                list.remove(i);
                list.remove(i);
                i = 0;
            }
        }
    }

    public void forSubtraction(List<String> list) {
        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).equals("-")) {
                double result = Double.parseDouble(list.get(i - 1)) - Double.parseDouble(list.get(i + 1));
                list.set(i - 1, Double.toString(result));
                list.remove(i);
                list.remove(i);
                i = 0;
            }
        }
    }


    public void settingUpArrayList() {
        for(int i = 0; !indexesOfOperations.isEmpty();){
            indexesOfOperations.remove(i);
        }
        indexesOfOperations.add(-1);
        for (int i = 0; i <= textField.getText().length() - 1; i++) {
            for (char operation : operations) {
                if (textField.getText().charAt(i) == operation) {
                    indexesOfOperations.add(i);
                }
            }
        }
        indexesOfOperations.add(textField.getText().length());
    }

    public void separatorString() {
        for (int i = 0; i <= indexesOfOperations.size() - 2; i++) {
            boolean isValid = false;
            for (int m = 0; m <= 6; m++) {
                try {
                    if (textField.getText().charAt(indexesOfOperations.get(i)) == operations[m]) {
                        isValid = true;
                    }
                } catch (Exception e) {

                }
            }
            if (isValid) {
                try {
                    separated.add(Character.toString(textField.getText().charAt(indexesOfOperations.get(i))));
                } catch (Exception e) {

                }
            }

            separated.add(textField.getText().substring(indexesOfOperations.get(i) + 1, indexesOfOperations.get(i + 1)));
        }
    }
}
