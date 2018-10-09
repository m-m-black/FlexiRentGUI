package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Apartment;
import model.PremiumSuite;
import model.PropertyStatus;
import model.PropertyType;
import view.MainProgramWindow;
import view.StartUp;

public class AddPropertyHandler implements EventHandler<ActionEvent> {
	
	private ComboBox<String> typeChoice;
	private TextField streetNumberField;
	private TextField streetNameField;
	private TextField suburbField;
	private ComboBox<Integer> bedroomsChoice;
	private TextArea descriptionField;
	private Button imageButton;
	
	private PropertyType type;
	private int streetNumber;
	private String streetName;
	private String suburb;
	private int bedrooms;
	private String description;
	private String image;
	
	public AddPropertyHandler(ComboBox<String> typeChoice, TextField streetNumberField, TextField streetNameField, 
			TextField suburbField, ComboBox<Integer> bedroomsChoice, TextArea descriptionField, Button imageButton) {
		this.typeChoice = typeChoice;
		this.streetNumberField = streetNumberField;
		this.streetNameField = streetNameField;
		this.suburbField = suburbField;
		this.bedroomsChoice = bedroomsChoice;
		this.descriptionField = descriptionField;
		this.imageButton = imageButton;
	}

	@Override
	public void handle(ActionEvent e) {
		// call RentalProperty constructor, passing in values from TextFields etc.
		type = getPropertyType(typeChoice);
		streetNumber = getStreetNumber(streetNumberField);
		streetName = streetNameField.getText();
		suburb = suburbField.getText();
		bedrooms = getBedrooms(bedroomsChoice);
		PropertyStatus status = PropertyStatus.Available;
		description = descriptionField.getText();
		image = imageButton.getText();
		
		if (image.compareTo("Choose image") == 0) {
			// image not selected, so set to default image
			image = "no_image.jpg";
		}
		
		if (!isValid()) {
			Alert alert = new Alert(AlertType.WARNING, "Details are missing");
			alert.showAndWait();
		} else {
			if (type.equals(PropertyType.Apartment)) {
				new Apartment(streetNumber, streetName, suburb, bedrooms, type, status, description, image);
			} else if (type.equals(PropertyType.PremiumSuite)) {
				new PremiumSuite(streetNumber, streetName, suburb, type, status, description, image);
			}
			StartUp.getList().populate();
			MainProgramWindow.setWindow("Home");
			Alert alert = new Alert(AlertType.INFORMATION, "Property has been added");
			alert.showAndWait();
		}
	}
	
	private int getStreetNumber(TextField streetNumberField) {
		int streetNumber = 0;
		try {
			streetNumber = Integer.valueOf(streetNumberField.getText());
		} catch (NumberFormatException n) {
			Alert alert = new Alert(AlertType.WARNING, "Street Number must be a number");
			alert.showAndWait();
		}
		return streetNumber;
	}
	
	private int getBedrooms(ComboBox<Integer> bedroomsChoice) {
		int bedrooms = 0;
		try {
			bedrooms = bedroomsChoice.getSelectionModel().getSelectedItem();
		} catch (NullPointerException n) {
			Alert alert = new Alert(AlertType.WARNING, "Number of bedrooms must be selected");
			alert.showAndWait();
		}
		return bedrooms;
	}
	
	private PropertyType getPropertyType(ComboBox<String> typeChoice) {
		PropertyType type = null;
		try {
			if (typeChoice.getSelectionModel().getSelectedItem().compareTo("Apartment") == 0) {
				type = PropertyType.Apartment;
			} else if (typeChoice.getSelectionModel().getSelectedItem().compareTo("Premium Suite") == 0) {
				type = PropertyType.PremiumSuite;
			}
		} catch (NullPointerException n) {
			Alert alert = new Alert(AlertType.WARNING, "Property Type must be selected");
			alert.showAndWait();
		}
		return type;
	}
	
	private boolean isValid() {
		boolean returnValue = false;
		try {
			if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() 
					|| streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() 
					|| descriptionField.getText().isEmpty()) {
				returnValue = false;
			} else {
				returnValue = true;
			}
		} catch (NullPointerException n) {
			
		}
		return returnValue;
	}

}
