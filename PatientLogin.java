package groupProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PatientLogin extends Application {

    private TextField patientIDField;
    private Stage primaryStage;
    private String patientName;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Login");

        // Create a GridPane for login components
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Image image = new Image("https://www.transparentpng.com/thumb/human/black-human-user-profile-png-icon-free-fsR5FT.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the width of the image
        imageView.setPreserveRatio(true);
        GridPane.setConstraints(imageView, 1, 0, 2, 1); // Center the image horizontally
        grid.getChildren().add(imageView);

        // Username Label
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 1);

        // Username Input
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Enter your username");
        GridPane.setConstraints(usernameInput, 1, 1);

        // Password Label
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 2);

        // Password Input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter your password");
        GridPane.setConstraints(passwordInput, 1, 2);

        // Login Button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 2, 3);
        // Span 1 column
        loginButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            // Check if the patient ID exists in the data source
            boolean isValid = checkPatientID(username, password);

            if (isValid) {
                // If login is successful, navigate to Nurse Portal
                PatientView patient = new PatientView();
                patient.setUsername(username);
                patient.start(primaryStage);
            } else {
                // If login fails, display an error message
                System.out.println("No account found with this patient ID. Please try again.");
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 3); // Span 1 column
        backButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width
        backButton.setOnAction(e -> {
            // Implement back button action here (e.g., navigate to a previous screen)
            // For demonstration, let's close the current window and open the home screen
            GroupProject home2 = new GroupProject();
            primaryStage.close();
            home2.start(primaryStage);
        });

        // Add components to the grid
        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton, backButton);

        // Create a BorderPane for layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid);

        // Set up the scene
        Scene scene = new Scene(borderPane, 800, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private boolean checkPatientID(String username, String password) {
        // Construct the file name based on the username (patient ID)
        String fileName = "nurseview_data_" + username + ".txt";

        // Check if the file exists
        File file = new File(fileName);
        return file.exists();
    }

    public static void main(String[] args) {
        launch(args);
    }
}