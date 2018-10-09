package view;

import java.util.HashMap;

import controller.BackButtonHandler;
import controller.ReturnButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.db.DatabaseMethods;

public class ReturnView extends BorderPane {
	
	private PropertyDetailWindow window;
	private static String propertyID;
	private String type;
	private static HashMap<String, String> currentRecord;
	private GridPane form = new GridPane();
	private static Label customerField;
	private static Label rentDateField;
	private static Label returnDateField;
	private static Label rentFeeField;
	private static Label lateFeeField;
	
	public ReturnView(PropertyDetailWindow window, String propertyIDString, String type) {
		this.window = window;
		propertyID = propertyIDString;
		this.type = type;
		currentRecord = DatabaseMethods.getRentalRecord(DatabaseMethods.getCurrentRecord(propertyID));
		makeForm();
		setCenter(form);
	}
	
	private void makeForm() {
		form.setHgap(10);
		form.setVgap(10);
		
		Label customerLabel = new Label("Customer ID: ");
		form.add(customerLabel, 0, 0);
		customerField = new Label(currentRecord.get("customerID"));
		form.add(customerField, 1, 0);
		
		Label rentDateLabel = new Label("Rent date: ");
		form.add(rentDateLabel, 0, 1);
		rentDateField = new Label(currentRecord.get("rentDate"));
		form.add(rentDateField, 1, 1);
		
		Label returnDateLabel = new Label("Estimated return date: ");
		form.add(returnDateLabel, 0, 2);
		returnDateField = new Label(currentRecord.get("estReturnDate"));
		form.add(returnDateField, 1, 2);
		
		Label actReturnDateLabel = new Label("Actual return date: ");
		form.add(actReturnDateLabel, 0, 3);
		DatePicker actReturnDatePicker = new DatePicker();
		form.add(actReturnDatePicker, 1, 3);
		
		Label rentFeeLabel = new Label("Rental fee: ");
		form.add(rentFeeLabel, 0, 4);
		rentFeeField = new Label("N/A");
		form.add(rentFeeField, 1, 4);
		
		Label lateFeeLabel = new Label("Late fee: ");
		form.add(lateFeeLabel, 0, 5);
		lateFeeField = new Label("N/A");
		form.add(lateFeeField, 1, 5);
		
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		form.add(backButton, 0, 6);
		Button returnButton = new Button("Return Property");
		returnButton.setOnAction(new ReturnButtonHandler(window, propertyID, type, actReturnDatePicker));
		form.add(returnButton, 1, 6);
	}
	
	public static void updateView() {
		currentRecord = DatabaseMethods.getRentalRecord(DatabaseMethods.getCurrentRecord(propertyID));
		customerField.setText(currentRecord.get("customerID"));
		rentDateField.setText(currentRecord.get("rentDate"));
		returnDateField.setText(currentRecord.get("estReturnDate"));
		// Update fee information
	}

}
