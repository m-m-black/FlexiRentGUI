package controller.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddPropertyHandler implements EventHandler<ActionEvent> {
	
	private ComboBox<String> type;
	private TextField streetNumber;
	private TextField streetName;
	private TextField suburb;
	private ComboBox<Integer> bedrooms;
	private TextArea description;
	//private File image;
	
	public AddPropertyHandler(ComboBox<String> type, TextField streetNumber, TextField streetName, 
			TextField suburb, ComboBox<Integer> bedrooms, TextArea description) {
		this.type = type;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.suburb = suburb;
		this.bedrooms = bedrooms;
		this.description = description;
		//this.image = image;
	}

	@Override
	public void handle(ActionEvent e) {
		System.out.println(type.getSelectionModel().getSelectedItem());
		System.out.println(streetNumber.getText());
		System.out.println(streetName.getText());
		System.out.println(suburb.getText());
		System.out.println(bedrooms.getSelectionModel().getSelectedItem());
		System.out.println(description.getText());
		//System.out.println(image.getName());
	}

}
