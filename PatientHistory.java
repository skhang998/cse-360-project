package groupProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PatientHistory extends Application {

    private TextField patientNameField;
    private TextField ageField;
    private TextField dobField;
    
    private TextArea medicationArea;
    private TextArea pharmacyArea;
    private TextArea healthIssuesArea;
    private TextArea immunizationArea;
    private TextArea allergiesArea;
    private TextArea previousVisitsArea;
    private String patientID;
    
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void start(Stage primaryStage) {
        Image image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                image, 
                BackgroundRepeat.NO_REPEAT, 
         
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
               
        );
        Background bg = new Background(bgImage);
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        
        patientNameField = new TextField();
        patientNameField.setPrefSize(150, 20);
        ageField = new TextField();
        ageField.setPrefSize(150, 20);
        dobField = new TextField();
        dobField.setPrefSize(150, 20);
        medicationArea = new TextArea();
        medicationArea.setPrefSize(150, 100);
        pharmacyArea = new TextArea();
        pharmacyArea.setPrefSize(150, 100);
        immunizationArea = new TextArea();
        immunizationArea.setPrefSize(150, 100);
        healthIssuesArea = new TextArea();
        healthIssuesArea.setPrefSize(150, 100);
        previousVisitsArea = new TextArea();
        previousVisitsArea.setPrefSize(150, 100);
        allergiesArea = new TextArea();
        allergiesArea.setPrefSize(150, 100);
        

        // Creating labels
       
        Label vitalsLabel = new Label("Vitals");
        vitalsLabel.setStyle("-fx-font-weight: bold");
        Label nurseInputLabel = new Label("Nurse Input");
        nurseInputLabel.setStyle("-fx-font-weight: bold");

        
        Button exitButton = new Button("Back");
        exitButton.setOnAction(e -> {
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        exitButton.setPrefWidth(70); // Set preferred width
        exitButton.setPrefHeight(30); // Set preferred height

        Button historyButton = new Button("History");
        historyButton.setOnAction(e -> {
            // Go back to the PatientHistory scene
            PatientHistory patient = new PatientHistory();
            patient.start(primaryStage);
        });
        historyButton.setPrefWidth(70); // Set preferred width
        historyButton.setPrefHeight(30); // Set preferred height

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save data
            saveData();
        });
        saveButton.setPrefWidth(70); // Set preferred width
        saveButton.setPrefHeight(30); // Set preferred height
        setPatientID(patientID);
        // Read data from file and fill in the fields
        fillVitalsFromFile("nurseview_data_" + patientID + ".txt");

        VBox nameBox = new VBox();
        nameBox.getChildren().addAll(new Label("Name:"), patientNameField);
        nameBox.setSpacing(5);

        VBox ageBox = new VBox();
        ageBox.getChildren().addAll(new Label("Age:"), ageField);
        ageBox.setSpacing(5);
        VBox dobBox = new VBox();
        dobBox.getChildren().addAll(new Label("DOB:"), dobField);
        dobBox.setSpacing(5);

        gridPane.add(nameBox, 0, 1);
        gridPane.add(dobBox, 2, 1);
        gridPane.add(ageBox, 5, 1);
        
        
        VBox medicationBox = new VBox();
        Label medicationLabel = new Label("Medication:");
        medicationLabel.setAlignment(Pos.CENTER);
        medicationBox.getChildren().addAll(medicationLabel, medicationArea);
        medicationBox.setSpacing(5);

        VBox pharmacyBox = new VBox();
        Label pharmacyLabel = new Label("Pharmacy:");
        pharmacyLabel.setAlignment(Pos.CENTER);
        pharmacyBox.getChildren().addAll(pharmacyLabel, pharmacyArea);
        pharmacyBox.setSpacing(5);

        VBox immunizationBox = new VBox();
        Label immunizationLabel = new Label("Immunization:");
        immunizationLabel.setAlignment(Pos.CENTER);
        immunizationBox.getChildren().addAll(immunizationLabel, immunizationArea);
        immunizationBox.setSpacing(5);
        
        VBox allergiesBox = new VBox();
        Label allergiesLabel = new Label("Allergies:");
        allergiesLabel.setAlignment(Pos.CENTER);
        allergiesBox.getChildren().addAll(allergiesLabel, allergiesArea);
        allergiesBox.setSpacing(5);
        
        VBox healthIssuesBox = new VBox();
        Label healthIssuesLabel = new Label("Health Issues:");
        allergiesLabel.setAlignment(Pos.CENTER);
        healthIssuesBox.getChildren().addAll(healthIssuesLabel, healthIssuesArea);
        healthIssuesBox.setSpacing(5);
        

        VBox previousVisitsBox = new VBox();
        Label previousVisitsLabel = new Label("Previous Visits:");
        previousVisitsLabel.setAlignment(Pos.CENTER);
        previousVisitsBox.getChildren().addAll(previousVisitsLabel, previousVisitsArea);
        previousVisitsBox.setSpacing(5);

        

        gridPane.add(medicationBox, 5, 3);
        gridPane.add(pharmacyBox, 6, 3);
        gridPane.add(immunizationBox, 5, 4);
        gridPane.add(previousVisitsBox, 6, 4);
        gridPane.add(allergiesBox, 5, 5);
        gridPane.add(healthIssuesBox, 6, 5);
        
 // Span 2 columns
        

        // Adding buttons to the layout
        gridPane.add(exitButton, 0, 11);
        
        gridPane.add(saveButton, 8, 11);
        
       
        
      
        gridPane.setBackground(bg);
        
        
        
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
     
        
        
        
        
        patientNameField = new TextField();
        patientNameField.setPrefSize(150, 20);
        ageField = new TextField();
        ageField.setPrefSize(150, 20);
        dobField = new TextField();
        dobField.setPrefSize(150, 20);
        medicationArea = new TextArea();
        medicationArea.setPrefSize(150, 100);
        pharmacyArea = new TextArea();
        pharmacyArea.setPrefSize(150, 100);
        immunizationArea = new TextArea();
        immunizationArea.setPrefSize(150, 100);
        healthIssuesArea = new TextArea();
        healthIssuesArea.setPrefSize(150, 100);
        previousVisitsArea = new TextArea();
        previousVisitsArea.setPrefSize(150, 100);
        allergiesArea = new TextArea();
        allergiesArea.setPrefSize(150, 100);
        

        // Creating labels
       
        
    }
    
    // Method to fill vitals from file
    private void fillVitalsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean startVitals = false;
            while ((line = reader.readLine()) != null) {
                // Check if we reached the vitals section
                if (line.startsWith("Vitals:")) {
                    startVitals = true;
                    continue; // Skip this line
                }
                // Check if we reached the nurse input section
                if (line.startsWith("Nurse Input:")) {
                    break; // Stop reading if we reached the nurse input section
                }
                // If we are in the vitals section, parse the data
                if (startVitals) {
                    String[] parts = line.split(": ");
                    switch (parts[0]) {
                        case "Name":
                            patientNameField.setText(parts[1]);
                            break;
                        case "Age":
                            ageField.setText(parts[1]);
                            break;
                        
                    }
                } else if (line.startsWith("Name:")) { // Check if the line contains patient name
                    patientNameField.setText(line.substring(6)); // Skip "Name: " to get the name
                } else if (line.startsWith("Age:")) { // Check if the line contains patient age
                    ageField.setText(line.substring(5)); // Skip "Age: " to get the age
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void saveData() {
        // Retrieve and save data
        String patientName = patientNameField.getText();
        String age = ageField.getText();
        String dob = dobField.getText();
        String med = medicationArea.getText();
        String pharm = pharmacyArea.getText();
        String imm = immunizationArea.getText();
        String healthIssues = healthIssuesArea.getText();
        String allergies = allergiesArea.getText();
        String prev = previousVisitsArea.getText();
        

        // Perform saving operation (printing to console in this example)
        System.out.println("Saving patient data:");
        System.out.println("Name: " + patientName);
        System.out.println("Age: " + age);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Saving patient vitals:");
        
        System.out.println("Medication: " + med);
        System.out.println("Pharmacy: " + pharm);
        System.out.println("Immunization: " + imm);
        System.out.println("Health Issues: " + healthIssues);
        System.out.println("Allergies: " + allergies);
        System.out.println("Previous Visits: " + prev);
        
    }
    
    // Method to fill vitals from file
   
    public static void main(String[] args) {
        launch(args);
    }
}