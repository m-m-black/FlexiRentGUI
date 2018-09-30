package view;

import controller.listeners.BackButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ReturnView extends BorderPane {
	
	private GridPane form = new GridPane();
	
	public ReturnView() {
		makeForm();
		setCenter(form);
	}
	
	private void makeForm() {
		form.setHgap(10);
		form.setVgap(10);
		
		Label customerLabel = new Label("Customer ID: ");
		form.add(customerLabel, 0, 0);
		Label customerField = new Label("placeholder_id");
		form.add(customerField, 1, 0);
		
		Label rentDateLabel = new Label("Rent date: ");
		form.add(rentDateLabel, 0, 1);
		Label rentDateField = new Label("placeholder_date");
		form.add(rentDateField, 1, 1);
		
		Label returnDateLabel = new Label("Estimated return date: ");
		form.add(returnDateLabel, 0, 2);
		Label returnDateField = new Label("placeholder_date");
		form.add(returnDateField, 1, 2);
		
		Label actReturnDateLabel = new Label("Actual return date: ");
		form.add(actReturnDateLabel, 0, 3);
		DatePicker actReturnDatePicker = new DatePicker();
		form.add(actReturnDatePicker, 1, 3);
		
		Label rentFeeLabel = new Label("Rental fee: ");
		form.add(rentFeeLabel, 0, 4);
		Label rentFeeField = new Label("placeholder_fee");
		form.add(rentFeeField, 1, 4);
		
		Label lateFeeLabel = new Label("Late fee: ");
		form.add(lateFeeLabel, 0, 5);
		Label lateFeeField = new Label("placeholder_fee");
		form.add(lateFeeField, 1, 5);
		
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		form.add(backButton, 0, 6);
		Button returnButton = new Button("Return Property");
		form.add(returnButton, 1, 6);
	}

}
