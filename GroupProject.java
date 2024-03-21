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
import javafx.scene.layout.StackPane;

public class GroupProject extends Application
{
	private static final int WIDTH = 800, HEIGHT = 400;			
	
	//Main method to control application
	public static void main(String[] args)
	{
		launch(args);
	} //Method main
	
	//Start method to launch the JavaFX scene and stage
	public void start(Stage stage)
	{
		PatientHistory scene2 = new PatientHistory();
		StackPane root = new StackPane();
		root.getChildren().add(scene2);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Patient History");
		stage.setScene(scene);
		stage.show();
	} //Method start
} //End of class Group Project