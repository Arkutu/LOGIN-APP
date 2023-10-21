package com.example.lgi;

import com.myproject.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button cancelButton;

    @FXML
    private Label LoginMessageLabel;

    @FXML
    private Label LoginMessageLabel2;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent e) {


        if (usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            // LoginMessageLabel.setText("Try to login!");

            validateLogin();

        } else {
            LoginMessageLabel.setText("Please enter username and password.");
        }
    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from UserAccounts where username = ? and Password = ? ";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordField.getText());

            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    LoginMessageLabel2.setText("Welcome!!!");
                } else {
                    LoginMessageLabel.setText("Invalid Login, Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

