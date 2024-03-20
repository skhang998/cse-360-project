/*
 * Team: 		Th 29
 * Class: 		CSE 360 - Th 1:30pm
 * Professor: 	Lynn Robert Carter
 * Term: 		Spring 2024
 * Assignment: 	Group Project
 * Description: Medical Platform
 */

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class GroupProject extends Application
{
	//Class variables
	private static final int WIDTH = 800, HEIGHT = 400, BODY_FONT = 16, TITLE_FONT = 20;
	private BorderPane main;
	private Button order, cancel, confirm;
	private HashMap<CheckBox, Double> selectedFood;
	private HashMap<RadioButton, Double> selectedDrink;
	private double total;
	private TextArea displayBill;
	
	//Constructor to set all the values and layout of the application
	public GroupProject ()
	{		 
		//Initializing layouts		 
		main = new BorderPane();
		HBox body = new HBox();
		VBox foodBox = new VBox();
		VBox drinkBox = new VBox();
		VBox billPane = new VBox();
		displayBill = new TextArea("");
		
		//Setting prices
		final double eggPrice = 7.99;
		final double chickenPrice = 9.99;
		final double bagelPrice = 2.50;
		final double potatoPrice = 4.49;
		final double blackTeaPrice = 1.25;
		final double greenTeaPrice = 0.99;
		final double coffeePrice = 1.99;
		final double orangePrice = 2.25;
		total = 0;

		//Setting labels and fonts
		Label title = new Label("Joe's Deli");
		Label food = new Label("Eat: ");
		food.setFont(Font.font(null, 18));
		Label drinks = new Label("Drink: ");
		drinks.setFont(Font.font(null, TITLE_FONT));
		Label bill = new Label("Bill");
		bill.setFont(Font.font(null, TITLE_FONT));
		
		//Setting food CheckBoxes
		CheckBox egg = new CheckBox("Egg Sandwich");
		egg.setPadding(new Insets(5,5,5,0));
		egg.setFont(Font.font(null, BODY_FONT));
		CheckBox chicken = new CheckBox("Chicken Sandwich");
		chicken.setPadding(new Insets(5,5,5,0));
		chicken.setFont(Font.font(null, BODY_FONT));
		CheckBox bagel = new CheckBox("Bagel");
		bagel.setPadding(new Insets(5,5,5,0));
		bagel.setFont(Font.font(null, BODY_FONT));
		CheckBox potato = new CheckBox("Potato Salad");
		potato.setPadding(new Insets(5,5,50,0));
		potato.setFont(Font.font(null, BODY_FONT));
		
		//Adding food items to array
		selectedFood = new HashMap<CheckBox, Double>();
		selectedFood.put(egg, eggPrice);
		selectedFood.put(chicken, chickenPrice);
		selectedFood.put(bagel, bagelPrice);
		selectedFood.put(potato, potatoPrice);
		
		//Setting drink RadioButtons
		ToggleGroup group = new ToggleGroup();
		RadioButton blackTea = new RadioButton("Black Tea");
		blackTea.setPadding(new Insets(5,5,5,0));
		blackTea.setFont(Font.font(null, BODY_FONT));
		RadioButton greenTea = new RadioButton("Green Tea");
		greenTea.setPadding(new Insets(5,5,5,0));
		greenTea.setFont(Font.font(null, BODY_FONT));
		RadioButton coffee = new RadioButton("Coffee");
		coffee.setPadding(new Insets(5,5,5,0));
		coffee.setFont(Font.font(null, BODY_FONT));
		RadioButton orange = new RadioButton("Orange Juice");
		orange.setPadding(new Insets(5,5,50,0));
		orange.setFont(Font.font(null, BODY_FONT));
		
		//Add RadioButtons to a ToggleGroup to ensure single select
		blackTea.setToggleGroup(group);
		greenTea.setToggleGroup(group);
		coffee.setToggleGroup(group);
		orange.setToggleGroup(group);
		
		//Adding drink items to array
		selectedDrink = new HashMap<RadioButton, Double>();
		selectedDrink.put(blackTea, blackTeaPrice);
		selectedDrink.put(greenTea, greenTeaPrice);
		selectedDrink.put(coffee, coffeePrice);
		selectedDrink.put(orange, orangePrice);
		
		//Setting control buttons
		order = new Button("Order");
		order.setFont(Font.font(null, BODY_FONT));
		cancel = new Button("Cancel");
		cancel.setFont(Font.font(null, BODY_FONT));
		confirm = new Button("Confirm");
		confirm.setFont(Font.font(null, BODY_FONT));
				
		//Populating the VBox that holds the food options
		foodBox.getChildren().addAll(food, egg, chicken, bagel, potato, order);
		foodBox.setPadding(new Insets(50,10,10,50));
		
		//Populating the VBox that holds the drink options
		drinkBox.getChildren().addAll(drinks, blackTea, greenTea, coffee, orange, cancel);
		drinkBox.setPadding(new Insets(50,10,10,50));
		
		//Populating the BorderPane that holds the final bill
		billPane.setMinSize(200, 230);
		billPane.setMinSize(200, 230);
		billPane.setSpacing(17);
		billPane.setPadding(new Insets(10,10,10,50));
		billPane.setAlignment(Pos.CENTER);
		displayBill.setEditable(false);
		displayBill.setMouseTransparent(true);
		displayBill.setMinSize(200, 230);
		displayBill.setMaxSize(200, 230);
		displayBill.setFont(Font.font(null, 12));
		displayBill.setStyle("-fx-border-color: black");
		billPane.getChildren().addAll(bill, displayBill, confirm);

		//Populating a HBox as the body of the menu
		body.getChildren().addAll(foodBox, drinkBox, billPane);
		
		//Populating the BorderPane as the root
		title.setFont(Font.font(null,24));
		BorderPane.setAlignment(title, Pos.CENTER);
		main.setTop(title);
		main.setCenter(body);
		
		//Registering buttons onto EventHandler
		order.setOnAction(new ButtonHandler());
		cancel.setOnAction(new ButtonHandler());
		confirm.setOnAction(new ButtonHandler());		
	} //End of constructor
	
	//EventHandler class for button functionality
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			Button pressedButton = (Button) e.getSource();
			String printBill = ""; 
			DecimalFormat price = new DecimalFormat("0.00");
			
			//Order Button - Show bill without tax and leave buttons/check boxes selected
			if(pressedButton.equals(order))
			{
				printBill = calculateBill() + "\nTotal: $" + price.format(total);
				displayBill.setText(printBill);
			}
			//Cancel Button - Erase bill and clear all buttons/check boxes
			else if(pressedButton.equals(cancel))
			{
				clearChoices();
				displayBill.setText("");
			}
			//Confirm Button - Show bill with tax/final price and clear all buttons/check boxes
			else if(pressedButton.equals(confirm))
			{				
				final double salesTax = 1.07;
				printBill = calculateBill() + "\nTotal: $" + price.format(total) + "\n...Sales Tax: 7%";
				double finalBill = (total * salesTax);
				printBill += "\nFinal Bill: $" + price.format(finalBill);
				clearChoices();
				//Accounting for repeated presses of 'confirm' button
				if(total != 0)
					displayBill.setText(printBill);
				else
					displayBill.setText("");
			}
		}
	} //End of class ButtonHandler
	 
	//Method to iterate through Map of items/prices, sum the total price, and display selected food
	private String calculateBill()
	{
		total = 0;
		String printBill = ""; 
		for(HashMap.Entry<CheckBox, Double> food : selectedFood.entrySet())
		{
			if(food.getKey().isSelected())
			{
				total += food.getValue();
				printBill += food.getKey().getText() + "\n";
			}
		}
		for(HashMap.Entry<RadioButton, Double> drinks : selectedDrink.entrySet())
		{
			if(drinks.getKey().isSelected())
			{
				total += drinks.getValue();
				printBill += drinks.getKey().getText() + "\n";
			}
		}
		return printBill;
	} //Method calculateBill
	
	//Method to clear all check boxes and radio buttons
	private void clearChoices()
	{
		for(CheckBox food : selectedFood.keySet())			
			food.setSelected(false);
		for(RadioButton drinks : selectedDrink.keySet())
			drinks.setSelected(false);
	} //Method clearChoices
	
	//Main method to control application
	public static void main(String[] args)
	{
		launch(args);
	} //Method main
	
	//Start method to launch the JavaFX scene and stage
	public void start(Stage stage)
	{
		StackPane root = new StackPane();
		root.getChildren().add(main);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Joe's Deli");
		stage.setScene(scene);
		stage.show();
	} //Method start
} //End of class hw1