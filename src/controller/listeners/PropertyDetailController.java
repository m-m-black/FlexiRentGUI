package controller.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.AlertWindow;
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
		if (PropertyDetailWindow.getStatus().compareTo("Available") == 0) {
			AlertWindow.show("Not available");
		} else {
			PropertyDetailWindow.getRentView().setVisible(true);
			PropertyDetailWindow.getButtonsView().setVisible(false);
			PropertyDetailWindow.getRentView().toFront();
		}
	}
	
	public void handleReturn() {
		PropertyDetailWindow.getReturnView().setVisible(true);
		PropertyDetailWindow.getButtonsView().setVisible(false);
		PropertyDetailWindow.getReturnView().toFront();
	}
	
	public void handleMaintain() {
		PropertyDetailWindow.getMaintainView().setVisible(true);
		PropertyDetailWindow.getButtonsView().setVisible(false);
		PropertyDetailWindow.getMaintainView().toFront();
	}
	
	public void handleComplete() {
		PropertyDetailWindow.getCompleteView().setVisible(true);
		PropertyDetailWindow.getButtonsView().setVisible(false);
		PropertyDetailWindow.getCompleteView().toFront();
	}
	
	public static void setNotVisible() {
		PropertyDetailWindow.getRentView().setVisible(false);
		PropertyDetailWindow.getReturnView().setVisible(false);
		PropertyDetailWindow.getMaintainView().setVisible(false);
		PropertyDetailWindow.getCompleteView().setVisible(false);
	}

}
