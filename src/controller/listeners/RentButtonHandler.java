package controller.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		System.out.println(customerID.getText() + " " + rentDate.getValue().toString() + " " + returnDate.getValue().toString());
		System.out.println(DateTimeMethods.toString(rentDate.getValue().toString()));
	}

}
