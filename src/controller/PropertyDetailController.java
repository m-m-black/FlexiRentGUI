package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Apartment;
import model.DateTime;
import model.PremiumSuite;
import model.PropertyStatus;
import model.PropertyType;
import model.RentalProperty;
import model.exceptions.DateTimeException;
import model.exceptions.MaintenanceException;
import view.PropertyDetailWindow;

public class PropertyDetailController implements EventHandler<ActionEvent> {
	
	private PropertyDetailWindow window;
	private String propertyID;
	@SuppressWarnings("unused")
	private String type;
	private RentalProperty r;
	
	public PropertyDetailController(PropertyDetailWindow window, String propertyID, String type, VBox recordsView) {
		this.window = window;
		this.propertyID = propertyID;
		this.type = type;
		if (type.compareTo(PropertyType.Apartment.toString()) == 0) {
			r = new Apartment();
		} else if (type.compareTo(PropertyType.PremiumSuite.toString()) == 0) {
			r = new PremiumSuite();
		}
		new RentalRecordController(recordsView, propertyID);
	}
	
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
		try {
			r.performMaintenance(propertyID);
			Alert alert = new Alert(AlertType.INFORMATION, "Property is now under maintenance");
			alert.showAndWait();
			window.updateView();
		} catch (MaintenanceException e) {
			Alert alert = new Alert(AlertType.WARNING, e.getMessage());
			alert.showAndWait();
		}
	}
	
	public void handleComplete() {
		try {
			r.completeMaintenance(propertyID, new DateTime().toString());
			Alert alert = new Alert(AlertType.INFORMATION, "Maintenance is now complete");
			alert.showAndWait();
			window.updateView();
		} catch (DateTimeException e1) {
			Alert alert = new Alert(AlertType.WARNING, e1.getMessage());
			alert.showAndWait();
		} catch (MaintenanceException e2) {
			Alert alert = new Alert(AlertType.WARNING, e2.getMessage());
			alert.showAndWait();
		}
	}
	
	public static void setNotVisible() {
		PropertyDetailWindow.getRentView().setVisible(false);
		PropertyDetailWindow.getReturnView().setVisible(false);
	}

}
