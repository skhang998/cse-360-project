package groupProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;

public class NurseChat extends Application {
    private VBox chatBox; // To hold the saved inputs
    private TextField inputField;
    private StringBuilder chatLog;
    private ScrollPane scrollPane;

    private String patientID = "64233";

    public void start(Stage primaryStage) {
        // Initialize the StringBuilder for chat log
        chatLog = new StringBuilder();

        // Create a VBox to hold the chat messages
        chatBox = new VBox(5);
        chatBox.setAlignment(Pos.TOP_LEFT);
        chatBox.setPadding(new Insets(10));

        // Load chat log contents
        loadChatLog(patientID);

        // Create a ScrollPane to contain the chat history
        scrollPane = new ScrollPane(chatBox); // Use class-level field here
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0); // Scroll to bottom

        // Create a BorderPane to hold the scrollPane at the center
        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);

        // Create a VBox to hold the text field and buttons
        VBox inputContainer = new VBox(5);
        inputContainer.setPadding(new Insets(10));

        // Create a TextField for user input
        inputField = new TextField();
        inputField.setPrefWidth(20); // Set preferred width
        inputField.setPromptText("Type your message here...");

        // Create a Button to save the input
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(100); // Set preferred width
        sendButton.setOnAction(e -> {
            String message = inputField.getText().trim();
            if (!message.isEmpty()) {
                saveMessage(message); // Save user's message
                inputField.clear(); // Clear input field
            }
        });

        // Create a Button to close the chat
        Button closeButton = new Button("Close Chat");
        closeButton.setPrefWidth(100); // Set preferred width
        closeButton.setOnAction(e -> {
            // Save chat log and close the chat
            saveChatLog(patientID);
            NurseView nurseView = new NurseView();
            nurseView.start(primaryStage);
        });

        // Create a Button to exit the chat
        Button exitButton = new Button("Exit Chat");
        exitButton.setPrefWidth(100); // Set preferred width
        exitButton.setOnAction(e -> {
            clearChatLog(patientID);
            chatBox.getChildren().clear(); // Clear the chat box
            NurseView nurseView = new NurseView();
            nurseView.start(primaryStage);
        });

        // Add sendButton and closeButton to an HBox
        HBox buttonBox = new HBox(10, sendButton, closeButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add inputField, sendButton, and closeButton to inputContainer
        inputContainer.getChildren().addAll(inputField, sendButton, closeButton);
        // Add exitButton to inputContainer
        inputContainer.getChildren().add(exitButton);

        // Add inputContainer to the bottom of the BorderPane
        root.setBottom(inputContainer);
        // Set the alignment of inputContainer
        BorderPane.setAlignment(inputContainer, Pos.BOTTOM_CENTER);

        // Display chat log and save chat log
        displayChatLog();
        saveChatLog(patientID);

        // Create a scene
        Scene scene = new Scene(root, 800, 400);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();

        // Center the stage on the screen
        primaryStage.centerOnScreen();

        // Load background image
        Image image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        root.setBackground(bg);
    }

    // Method to load chat log contents from file
    private void loadChatLog(String patientID) {
        String fileName = "patient_chatlog_" + patientID + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatLog.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display chat log contents in the chat box
    private void displayChatLog() {
        String[] messages = chatLog.toString().split("\n");
        for (String message : messages) {
            Label chatLabel = new Label(message);
            chatLabel.setStyle("-fx-background-color: white; -fx-padding: 5px;");
            chatBox.getChildren().add(chatLabel);
        }
    }

    // Method to append messages to chat log and display them
    private void saveMessage(String message) {
        // Append current date and time to the message
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        String messageWithTimestamp = timestamp + " - Nurse: " + message;

        Label chatLabel = new Label(messageWithTimestamp);
        chatLabel.setStyle("-fx-background-color: white; -fx-padding: 5px;");
        chatBox.getChildren().add(chatLabel);
        appendToChatLog(messageWithTimestamp);

        // Scroll to the bottom
        chatBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    // Method to append messages to chat log
    private void appendToChatLog(String message) {
        chatLog.append(message).append("\n");
    }

    // Method to save the chat log to a text file
    private void saveChatLog(String patientID) {
        String fileName = "patient_chatlog_" + patientID + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(chatLog.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to clear the chat log and delete the file
    private void clearChatLog(String patientID) {
        // Delete the chat log file
        String fileName = "patient_chatlog_" + patientID + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }

        // Clear the StringBuilder for chat log
        chatLog.setLength(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}