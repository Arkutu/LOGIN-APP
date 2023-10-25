package com.example.myapp.controllers;



import javafx.event.ActionEvent;
import com.myproject.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistrationController {

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmpasswordField;

    @FXML
    private Button signupButton;

    @FXML
    private Button closeButton;

    @FXML
    private Hyperlink loginLink;






    public void signupAction() {
        String firstName = firstnameTextField.getText();
        String lastName = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmpassword = confirmpasswordField.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            try {
                String insertUser = "INSERT INTO UsersAccounts (FirstName, LastName, Username, Password, ConfirmPassword) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connectDB.prepareStatement(insertUser);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, confirmpassword);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("User registered successfully.");
                    // Close the registration page
                    ((Stage) firstnameTextField.getScene().getWindow()).close();
                } else {
                    System.out.println("Failed to register user.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Database connection failed.");
        }
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}



