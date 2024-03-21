package groupProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;

public class NurseView extends GridPane {

    private TextField patientNameField;
    private TextField ageField;
    private TextField dobField;
    private TextField weightField;
    private TextField heightField;
    private TextField bodyTempField;
    private TextField bloodPressureField;

    private TextArea healthIssuesArea;
    private TextArea allergiesArea;
    private TextArea purposeOfVisitArea;

    public NurseView() {
        Image image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                image, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        setBackground(bg);
        // Patient Information Section
        patientNameField = new TextField();
        patientNameField.setPromptText("Enter patient name");

        ageField = new TextField();
        ageField.setPromptText("Enter patient age");

        dobField = new TextField();
        dobField.setPromptText("Enter date of birth");

        Label patientInfoLabel = new Label("Patient Information");
        patientInfoLabel.setStyle("-fx-font-weight: bold");
        patientInfoLabel.setAlignment(Pos.CENTER);
        weightField = new TextField();
        weightField.setPromptText("Enter weight");

        heightField = new TextField();
        heightField.setPromptText("Enter height");

        bodyTempField = new TextField();
        bodyTempField.setPromptText("Enter body temperature");

        bloodPressureField = new TextField();
        bloodPressureField.setPromptText("Enter blood pressure");

        Label vitalsLabel = new Label("Vitals");
        vitalsLabel.setStyle("-fx-font-weight: bold");
       
        // Nurse Input Section
        healthIssuesArea = new TextArea();
        healthIssuesArea.setPromptText("Enter health issues");
        healthIssuesArea.setPrefRowCount(0); // Set rows for TextArea

        allergiesArea = new TextArea();
        allergiesArea.setPromptText("Enter allergies");
        allergiesArea.setPrefRowCount(0); // Set rows for TextArea

        purposeOfVisitArea = new TextArea();
        purposeOfVisitArea.setPromptText("Enter purpose of visit");
        purposeOfVisitArea.setPrefRowCount(0); // Set rows for TextArea

        Label nurseInputLabel = new Label("Nurse Input");
        nurseInputLabel.setStyle("-fx-font-weight: bold");

        // Set up layout
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(05);
        

        // Add elements to the layout
        add(patientInfoLabel, 1, 0);
        add(new Label("Name:"), 0, 1);
        add(patientNameField, 1, 1);
        add(new Label("Age:"), 0, 2);
        add(ageField, 1, 2);
        add(new Label("Date of Birth:"), 0, 3);
        add(dobField, 1, 3);
        add(vitalsLabel, 1, 4);
        add(new Label("Weight:"), 0, 5);
        add(weightField, 1, 5);
        add(new Label("Height:"), 0, 6);
        add(heightField, 1, 6);
        add(new Label("Body Temperature:"), 0, 7);
        add(bodyTempField, 1, 7);
        add(new Label("Blood Pressure:"), 0, 8);
        add(bloodPressureField, 1, 8);


        add(nurseInputLabel, 3, 0);
        add(new Label("Health Issues:"), 2, 1);
        add(healthIssuesArea, 3, 1);
        add(new Label("Allergies:"), 2, 2);
        add(allergiesArea, 3, 2);
        add(new Label("Purpose of Visit:"), 2, 3);
        add(purposeOfVisitArea, 3, 3);

        // Creating a back button
        Button history = new Button("History");
        history.setOnAction(e -> {
            // Go back to the PatientHistory scene
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(new StackPane(new PatientHistory()), 800, 400));
            stage.setTitle("Patient History");
        });

        // Creating a save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save edited data
            saveData();
        });

        // Add buttons
        add(history, 0, 9);
        add(saveButton, 1, 9);
    }

    private void saveData() {
        // Collect patient information
        String patientName = patientNameField.getText();
        String age = ageField.getText();
        String dob = dobField.getText();
        String weight = weightField.getText();
        String height = heightField.getText();
        String bodyTemp = bodyTempField.getText();
        String bloodP = bloodPressureField.getText();

        // Collect nurse input
        String healthIssues = healthIssuesArea.getText();
        String allergies = allergiesArea.getText();
        String purposeOfVisit = purposeOfVisitArea.getText();

        // Perform saving operation (printing to console in this example)
        System.out.println("Saving patient data:");
        System.out.println("Name: " + patientName);
        System.out.println("Age: " + age);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Saving patient vitals:");
        System.out.println("Weight: " + weight);
        System.out.println("Height: " + height);
        System.out.println("Body Temperature: " + bodyTemp);
        System.out.println("Blood Pressure: " + bloodP);

        System.out.println("Saving nurse input:");
        System.out.println("Health Issues: " + healthIssues);
        System.out.println("Allergies: " + allergies);
        System.out.println("Purpose of Visit: " + purposeOfVisit);
    }
}
