package groupProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NurseView extends Application {

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

    @Override
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

        // Creating UI components
        patientNameField = new TextField();
        ageField = new TextField();
        dobField = new TextField();
        weightField = new TextField();
        heightField = new TextField();
        bodyTempField = new TextField();
        bloodPressureField = new TextField();
        healthIssuesArea = new TextArea();
        allergiesArea = new TextArea();
        purposeOfVisitArea = new TextArea();

        // Creating labels
        Label patientInfoLabel = new Label("Patient Information");
        patientInfoLabel.setStyle("-fx-font-weight: bold");
        Label vitalsLabel = new Label("Vitals");
        vitalsLabel.setStyle("-fx-font-weight: bold");
        Label nurseInputLabel = new Label("Nurse Input");
        nurseInputLabel.setStyle("-fx-font-weight: bold");

        // Creating buttons
        Button exitButton = new Button("Back");
        exitButton.setOnAction(e -> {
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        
        Button historyButton = new Button("History");
        historyButton.setOnAction(e -> {
            // Go back to the PatientHistory scene
            PatientHistory patient = new PatientHistory();
            patient.start(primaryStage);
        });
       
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save data
            saveData();
        });
        
        // Creating the layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Adding components to the layout
        gridPane.add(patientInfoLabel, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(patientNameField, 1, 1);
        gridPane.add(new Label("Age:"), 0, 2);
        gridPane.add(ageField, 1, 2);
        gridPane.add(new Label("Date of Birth:"), 0, 3);
        gridPane.add(dobField, 1, 3);
        gridPane.add(vitalsLabel, 1, 4);
        gridPane.add(new Label("Weight:"), 0, 5);
        gridPane.add(weightField, 1, 5);
        gridPane.add(new Label("Height:"), 0, 6);
        gridPane.add(heightField, 1, 6);
        gridPane.add(new Label("Body Temperature:"), 0, 7);
        gridPane.add(bodyTempField, 1, 7);
        gridPane.add(new Label("Blood Pressure:"), 0, 8);
        gridPane.add(bloodPressureField, 1, 8);

        gridPane.add(nurseInputLabel, 3, 0);
        gridPane.add(new Label("Health Issues:"), 2, 1);
        gridPane.add(healthIssuesArea, 3, 1);
        gridPane.add(new Label("Allergies:"), 2, 2);
        gridPane.add(allergiesArea, 3, 2);
        gridPane.add(new Label("Purpose of Visit:"), 2, 3);
        gridPane.add(purposeOfVisitArea, 3, 3);

        // Adding buttons to the layout
        gridPane.add(exitButton, 0, 9);
        gridPane.add(historyButton, 1, 9);
        gridPane.add(saveButton, 2, 9);
       
        // Setting background
        gridPane.setBackground(bg);

        // Creating and showing the scene
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nurse View");
        primaryStage.show();
    }

    private void saveData() {
        // Retrieve and save data
        String patientName = patientNameField.getText();
        String age = ageField.getText();
        String dob = dobField.getText();
        String weight = weightField.getText();
        String height = heightField.getText();
        String bodyTemp = bodyTempField.getText();
        String bloodPressure = bloodPressureField.getText();
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
        System.out.println("Blood Pressure: " + bloodPressure);
        System.out.println("Saving nurse input:");
        System.out.println("Health Issues: " + healthIssues);
        System.out.println("Allergies: " + allergies);
        System.out.println("Purpose of Visit: " + purposeOfVisit);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
