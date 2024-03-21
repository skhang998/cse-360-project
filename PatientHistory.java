package groupProject;

import java.util.HashMap;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class PatientHistory extends HBox {
	private static final int WIDTH = 800, HEIGHT = 400, BODY_FONT = 16, TITLE_FONT = 20;
	
	private Label title;
	private VBox col1, col2, col3;
	private Button back, exit;
	private TextArea patName, patAge, patDOB, med, immunize, pharma, allerg, health, visits;
	
	public PatientHistory()
	{
		col1 = new VBox();
		title = new Label();
		patName = new TextArea();
		patAge = new TextArea();
		patDOB = new TextArea();
		
	    //col1.setPadding(new Insets(0, 0, 0, 0));
	    col1.setStyle("-fx-border-color: black");
	    col1.setMinSize(400, 375);
        col1.setMaxSize(400, 375);
        title.setText("Patient History");
        title.setFont(Font.font(null, TITLE_FONT));
        title.setPadding(new Insets(0, 0, 50, 0));
        col1.setAlignment(Pos.TOP_CENTER);
        
        patName.setMaxSize(200, 40);
        patName.setMinSize(200, 40);
        patName.setEditable(false);
		patName.setMouseTransparent(true);
        patAge.setMaxSize(200, 40);
        patAge.setMinSize(200, 40);
        patAge.setEditable(false);
		patAge.setMouseTransparent(true);
        patDOB.setMaxSize(200, 40);
        patDOB.setMinSize(200, 40);
        patDOB.setEditable(false);
		patDOB.setMouseTransparent(true);

        col1.getChildren().addAll(title, patName, patAge, patDOB);
	    
	    this.getChildren().addAll(col1);
	}



}
