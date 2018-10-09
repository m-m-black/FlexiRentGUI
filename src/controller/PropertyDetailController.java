package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.PropertyStatus;
import view.PropertyDetailWindow;

public class PropertyDetailController implements EventHandler<ActionEvent> {
	
	@Override
	public void handle(ActionEvent e) {
		String event = ((Button) e.getSource()).getText();
		switch (event) {
			case "Rent": 				handleRent();
										break;
			case "Return": 				handleReturn();
										break;
			case "Maintenance": 			handleMaintain();
										break;
			case "Complete Maintenance":	handleComplete();
										break;
		}
	}
	
	public void handleRent() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.Available.toString()) == 0) {
			PropertyDetailWindow.getRentView().setVisible(true);
			PropertyDetailWindow.getButtonsView().setVisible(false);
			PropertyDetailWindow.getRentView().toFront();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Property is not available for rent");
			alert.showAndWait();
		}
	}
	
	public void handleReturn() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.Rented.toString()) == 0) {
			PropertyDetailWindow.getReturnView().setVisible(true);
			PropertyDetailWindow.getButtonsView().setVisible(false);
			PropertyDetailWindow.getReturnView().toFront();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Property is not currently being rented");
			alert.showAndWait();
		}
	}
	
	public void handleMaintain() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.Available.toString()) == 0) {
			// set property status to UnderMaintenance
			Alert alert = new Alert(AlertType.INFORMATION, "Property is now under maintenance");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Property is not available for maintenance");
			alert.showAndWait();
		}
	}
	
	public void handleComplete() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.UnderMaintenance.toString()) == 0) {
			// set property status to available
			Alert alert = new Alert(AlertType.INFORMATION, "Maintenance is now complete");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Property is not currently under maintenance");
			alert.showAndWait();
		}
	}
	
	public static void setNotVisible() {
		PropertyDetailWindow.getRentView().setVisible(false);
		PropertyDetailWindow.getReturnView().setVisible(false);
	}

}
