package controller.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.PropertyStatus;
import view.AlertWindow;
import view.ConfirmationWindow;
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
			AlertWindow.show("Property is not available for rent");
		}
	}
	
	public void handleReturn() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.Rented.toString()) == 0) {
			PropertyDetailWindow.getReturnView().setVisible(true);
			PropertyDetailWindow.getButtonsView().setVisible(false);
			PropertyDetailWindow.getReturnView().toFront();
		} else {
			AlertWindow.show("Property is not currently being rented");
		}
	}
	
	public void handleMaintain() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.Available.toString()) == 0) {
			// set property status to UnderMaintenance
			ConfirmationWindow.show("Property is now under maintenance");
		} else {
			AlertWindow.show("Property is not available for maintenance");
		}
	}
	
	public void handleComplete() {
		if (PropertyDetailWindow.getStatus().compareTo(PropertyStatus.UnderMaintenance.toString()) == 0) {
			// set property status to available
			ConfirmationWindow.show("Maintenance is not complete");
		} else {
			AlertWindow.show("Property is not currently under maintenance");
		}
	}
	
	public static void setNotVisible() {
		PropertyDetailWindow.getRentView().setVisible(false);
		PropertyDetailWindow.getReturnView().setVisible(false);
	}

}
