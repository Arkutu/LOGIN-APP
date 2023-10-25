package com.example.lgi;

import com.myproject.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.IOException;

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

    @FXML
    private Hyperlink signupLink;





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

        if (connectDB != null) {
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
        } else {
            LoginMessageLabel.setText("Database connection failed.");
        }
    }
      @FXML
    private void handleSignUpLinkClick(ActionEvent event) {
        // Load the registration page or open a new stage for it
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 529, 742); // Set the desired width and height
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            // Optionally, you can close the login page if needed
            // ((Stage) signUpLink.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}


