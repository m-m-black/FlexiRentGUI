package view;

import controller.listeners.BackButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class RentView extends BorderPane {
	
	private GridPane form = new GridPane();
	
	public RentView() {
		makeForm();
		setCenter(form);
	}
	
	private void makeForm() {
		form.setHgap(10);
		form.setVgap(10);
		
		Label customerLabel = new Label("Customer ID: ");
		form.add(customerLabel, 0, 0);
		TextField customerField = new TextField();
		form.add(customerField, 1, 0);
		
		Label rentDateLabel = new Label("Rent date: ");
		form.add(rentDateLabel, 0, 1);
		DatePicker rentDatePicker = new DatePicker();
		form.add(rentDatePicker, 1, 1);
		
		Label returnDateLabel = new Label("Return date: ");
		form.add(returnDateLabel, 0, 2);
		DatePicker returnDatePicker = new DatePicker();
		form.add(returnDatePicker, 1, 2);
		
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		form.add(backButton, 0, 3);
		Button rentButton = new Button("Rent");
		form.add(rentButton, 1, 3);
	}

}
