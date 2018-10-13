package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import model.Apartment;
import model.PremiumSuite;
import model.PropertyType;
import model.RentalProperty;
import model.db.DateTimeMethods;
import model.exceptions.ReturnException;
import view.PropertyDetailWindow;
import view.ReturnView;
import view.StartUp;

public class ReturnButtonHandler implements EventHandler<ActionEvent> {
	
	private PropertyDetailWindow window;
	private String propertyID;
	private String type;
	private DatePicker actReturnDate;
	private String actReturnDateString;
	
	public ReturnButtonHandler(PropertyDetailWindow window, String propertyID, String type, DatePicker actReturnDate) {
		this.window = window;
		this.propertyID = propertyID;
		this.type = type;
		this.actReturnDate = actReturnDate;
	}

	@Override
	public void handle(ActionEvent e) {
		try {
			actReturnDateString = DateTimeMethods.toString(actReturnDate.getValue().toString());
			returnProperty(actReturnDateString);
			Alert alert = new Alert(AlertType.INFORMATION, "Property has now been returned");
			alert.showAndWait();
			window.updateView();
			StartUp.refresh();
			ReturnView.updateView();
		} catch (NullPointerException n) {
			n.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING, "Date information is missing");
			alert.showAndWait();
		}
	}
	
	private void returnProperty(String actReturnDateString) {
		if (type.compareTo(PropertyType.Apartment.toString()) == 0) {
			RentalProperty r = new Apartment();
			try {
				r.returnProperty(propertyID, actReturnDateString);
			} catch (ReturnException e) {
				Alert alert = new Alert(AlertType.WARNING, "Something went wrong: ReturnButtonHandler.returnProperty()[1]");
				alert.showAndWait();
			}
		} else if (type.compareTo(PropertyType.PremiumSuite.toString()) == 0) {
			RentalProperty r = new PremiumSuite();
			try {
				r.returnProperty(propertyID, actReturnDateString);
			} catch (ReturnException e) {
				Alert alert = new Alert(AlertType.WARNING, "Something went wrong: ReturnButtonHandler.returnProperty()[2]");
				alert.showAndWait();
			}
		}
	}

}
