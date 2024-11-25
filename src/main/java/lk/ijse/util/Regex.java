package lk.ijse.util;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isTextFieldValid(TextFieldType fieldType, String text) {
        String patternString = "";


        switch (fieldType) {
            case NAME:
                patternString = "^[A-z\\s]{3,}$";
                break;
            case CONTACT:
                patternString = "^([+]94|0)([1-9]{2})([0-9]{7})$";
                break;
            case NIC:
                patternString = "^([0-9]{9}[xXvV]|[0-9]{12})$";
                break;
            case ADDRESS:
                patternString = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
                break;
            case PRICE:
                patternString = "^[0-9]+(\\.[0-9]+)?$";
                break;
            case QUANTITY:
                patternString = "^\\d+$";
                break;
            case PASSWORD:
                patternString = "^.*\\d.*\\d.*\\d.*$";
                break;
            case USERNAME:
                patternString = "^[a-zA-Z0-9_.]+$";
                break;
            case DATE:
                patternString = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
                break;
        }

        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    public static boolean setTextColor(TextFieldType fieldType, TextField textField) {
        if (isTextFieldValid(fieldType, textField.getText())) {
            textField.setStyle("-fx-background-color: #B0EBB4;");
            return true;
        } else {
            textField.setStyle("-fx-background-color: #FA26A0;");
            return false;
        }
    }
}
