package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.db.DateTimeMethods;

public class RentButtonHandler implements EventHandler<ActionEvent> {
	
	private TextField customerID;
	private DatePicker rentDate;
	private DatePicker returnDate;
	
	public RentButtonHandler(TextField customerID, DatePicker rentDate, DatePicker returnDate) {
		this.customerID = customerID;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}

	@Override
	public void handle(ActionEvent e) {
		// create new RentalRecord, after validation
		if (customerID.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "Customer ID is empty");
			alert.showAndWait();
		} else if (customerID.getText().length() > 6) {
			Alert alert = new Alert(AlertType.WARNING, "Customer ID must be 6 or fewer characters");
			alert.showAndWait();
		} else {
			String customerIDString = customerID.getText();
		}
		try {
			String rentDateString = DateTimeMethods.toString(rentDate.getValue().toString());
			String returnDateString = DateTimeMethods.toString(returnDate.getValue().toString());
			// Make new RentalRecord
		} catch (NullPointerException n) {
			Alert alert = new Alert(AlertType.WARNING, "Date information is missing");
			alert.showAndWait();
		}
	}

}
