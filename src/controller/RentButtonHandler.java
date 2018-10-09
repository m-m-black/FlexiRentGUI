package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Apartment;
import model.PremiumSuite;
import model.PropertyType;
import model.db.DateTimeMethods;
import model.exceptions.RentException;
import view.PropertyDetailWindow;
import view.ReturnView;
import view.StartUp;

public class RentButtonHandler implements EventHandler<ActionEvent> {
	
	private PropertyDetailWindow window;
	private String propertyID;
	private String type;
	private TextField customerID;
	private DatePicker rentDate;
	private DatePicker returnDate;
	private String customerIDString;
	private String rentDateString;
	private String returnDateString;
	
	public RentButtonHandler(PropertyDetailWindow window, String propertyID, String type, 
			TextField customerID, DatePicker rentDate, DatePicker returnDate) {
		this.window = window;
		this.propertyID = propertyID;
		this.type = type;
		this.customerID = customerID;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}

	@Override
	public void handle(ActionEvent e) {
		// create new RentalRecord, after validation
		customerIDString = new String();
		if (customerID.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "Customer ID is empty");
			alert.showAndWait();
		} else if (customerID.getText().length() > 6) {
			Alert alert = new Alert(AlertType.WARNING, "Customer ID must be 6 or fewer characters");
			alert.showAndWait();
		} else {
			customerIDString = customerID.getText();
		}
		try {
			rentDateString = DateTimeMethods.toString(rentDate.getValue().toString());
			returnDateString = DateTimeMethods.toString(returnDate.getValue().toString());
			rent();
		} catch (NullPointerException n) {
			Alert alert = new Alert(AlertType.WARNING, "Date information is missing");
			alert.showAndWait();
		}
	}
	
	private void rent() {
		try {
			if (type.compareTo(PropertyType.Apartment.toString()) == 0) {
				Apartment.rent(propertyID, customerIDString, rentDateString, returnDateString);
			} else if (type.compareTo(PropertyType.PremiumSuite.toString()) == 0) {
				PremiumSuite.rent(propertyID, customerIDString, rentDateString, returnDateString);
			}
			Alert alert = new Alert(AlertType.INFORMATION, "Property is now being rented");
			alert.showAndWait();
			window.updateView();
			StartUp.refresh();
			ReturnView.updateView();
		} catch (RentException e) {
			Alert alert = new Alert(AlertType.WARNING, e.getMessage());
			alert.showAndWait();
		}
	}

}
