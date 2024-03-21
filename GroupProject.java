/*
 * Team: 		Th 29
 * Class: 		CSE 360 - Th 1:30pm
 * Professor: 	Lynn Robert Carter
 * Term: 		Spring 2024
 * Assignment: 	Group Project
 * Description: Medical Platform
 */

package groupProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class GroupProject extends Application
{
	private static final int WIDTH = 800, HEIGHT = 400;		
	private Stage stage;
	private Scene nurseView, patientHistory;
	
	private Scene createPH()
	{
		PatientHistory ph = new PatientHistory();
		patientHistory = new Scene(ph, WIDTH, HEIGHT);
		return patientHistory;
	} //method createPH
	
	private Scene createNV()
	{
		NurseView nv = new NurseView();
		nurseView = new Scene(nv, WIDTH, HEIGHT);
		return nurseView;
	} //method createNV
	
	public void nurseToHistory(Button pressed)
	{
		if (pressed.getText().equals("Back"))
			stage.setScene(nurseView);
		else if (pressed.getText().equals("Patient History"))
			stage.setScene(patientHistory);
	} //method nurseToHistory
	
	//Main method to control application
	public static void main(String[] args)
	{
		launch(args);
	} //Method main
	
	//Start method to launch the JavaFX scene and stage
	public void start(Stage stage)
	{
		this.stage = stage;
		//NurseView scene2 = new NurseView();
		//PatientHistory scene3 = new PatientHistory();
		//StackPane root = new StackPane();
		//root.getChildren().add(scene3);
		//Scene scene = new Scene(root, WIDTH, HEIGHT);
		createPH();
		createNV();
		stage.setTitle("Nurse Portal");
		stage.setScene(nurseView);
		stage.show();
	} //Method start
} //End of class Group Project