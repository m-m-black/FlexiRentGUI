package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import model.db.DateTimeMethods;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

public class ReturnButtonHandler implements EventHandler<ActionEvent> {
	
	private DatePicker actReturnDate;
	
	public ReturnButtonHandler(DatePicker actReturnDate) {
		this.actReturnDate = actReturnDate;
	}

	@Override
	public void handle(ActionEvent e) {
		try {
			String actReturnDateString = DateTimeMethods.toString(actReturnDate.getValue().toString());
		} catch (NullPointerException n) {
			Alert alert = new Alert(AlertType.WARNING, "Date information is missing");
			alert.showAndWait();
		}
	}

}
