package groupProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DoctorView extends Application  {
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
        
        Button exitButton = new Button("Back");
        exitButton.setOnAction(e -> {
        GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        
        gridPane.add(exitButton, 0, 9);
        gridPane.setBackground(bg);
        
        
        
        
        
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor View");
        primaryStage.show();
        
	}
	public static void main(String[] args) {
	        launch(args);
	    }
	
}
