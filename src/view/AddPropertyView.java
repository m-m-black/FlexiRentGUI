package view;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AddPropertyView extends BorderPane {
	
	private static final int MAX_CHARS = 100;
	GridPane form = new GridPane();
	
	public AddPropertyView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new Label("Add Property"));
		this.makeForm();
		this.setCenter(form);
	}
	
	private void makeForm() {
		form.setHgap(10);
		form.setVgap(10);

		Label type = new Label("Property Type:");
		form.add(type, 0, 1);

		ComboBox<String> typeChoice = new ComboBox<String>(
				FXCollections.observableArrayList("Apartment", "Premium Suite"));
		typeChoice.setPromptText("property type");
		form.add(typeChoice, 1, 1);

		Label streetNumber = new Label("Street Number:");
		form.add(streetNumber, 0, 2);

		TextField streetNumberTextField = new TextField();
		streetNumberTextField.setMaxWidth(75);
		form.add(streetNumberTextField, 1, 2);
		
		Label streetName = new Label("Street Name:");
		form.add(streetName, 0, 3);
		
		TextField streetNameTextField = new TextField();
		streetNameTextField.setMaxWidth(200);
		form.add(streetNameTextField, 1, 3);
		
		Label suburb = new Label("Suburb:");
		form.add(suburb, 0, 4);
		
		TextField suburbTextField = new TextField();
		suburbTextField.setMaxWidth(200);
		form.add(suburbTextField, 1, 4);
		
		Label bedrooms = new Label("Bedrooms:");
		form.add(bedrooms, 0, 5);
		
		ComboBox<Integer> bedroomsChoice = new ComboBox<Integer>(
				FXCollections.observableArrayList(1, 2, 3));
		bedroomsChoice.setPromptText("number of bedrooms");
		form.add(bedroomsChoice, 1, 5);
		
		Label description = new Label("Description:");
		form.add(description, 0, 6);
		
		TextArea descriptionTextArea = new TextArea();
		descriptionTextArea.setMaxHeight(75);
		descriptionTextArea.setMaxWidth(300);
		descriptionTextArea.setTextFormatter(new TextFormatter<String>(change ->
			change.getControlNewText().length() <= MAX_CHARS ? change : null));
		descriptionTextArea.setWrapText(true);
		form.add(descriptionTextArea, 1, 6);
		
		Label image = new Label("Image:");
		form.add(image, 0, 7);
		
		TextField imageTextField = new TextField();
		form.add(imageTextField, 1, 7);
		
		Button addButton = new Button("Add Property");
		form.add(addButton, 1, 8);
	}

}
