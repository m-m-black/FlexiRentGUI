package controller.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		int streetNumber = Integer.valueOf(streetNumberField.getText());
		String streetName = streetNameField.getText();
		String suburb = suburbField.getText();
		int bedrooms = bedroomsChoice.getSelectionModel().getSelectedItem();
		PropertyType type = getPropertyType(typeChoice);
		if (typeChoice.getSelectionModel().getSelectedItem().compareTo("Apartment") == 0) {
			type = PropertyType.Apartment;
		} else if ((typeChoice.getSelectionModel().getSelectedItem().compareTo("Premium Suite") == 0)) {
			type = PropertyType.PremiumSuite;
		}
		PropertyStatus status = PropertyStatus.Available;
		String description = descriptionField.getText();
		String image = imageButton.getText();
		if (type.equals(PropertyType.Apartment)) {
			new Apartment(streetNumber, streetName, suburb, bedrooms, type, status, description, image);
		} else if (type.equals(PropertyType.PremiumSuite)) {
			new PremiumSuite(streetNumber, streetName, suburb, type, status, description, image);
		}
		StartUp.getList().populate();
		MainProgramWindow.setWindow("Home");
	}
	
	private PropertyType getPropertyType(ComboBox<String> typeChoice) {
		if (typeChoice.getSelectionModel().getSelectedItem().compareTo("Apartment") == 0) {
			return PropertyType.Apartment;
		} else if (typeChoice.getSelectionModel().getSelectedItem().compareTo("Premium Suite") == 0) {
			return PropertyType.PremiumSuite;
		} else {
			return null;
		}
	}

}
