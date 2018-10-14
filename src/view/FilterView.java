package view;

import controller.FilterController;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class FilterView extends HBox {
	
	private ComboBox<String> suburbSelector;
	private ComboBox<String> typeSelector;
	private ComboBox<Integer> bedroomsSelector;
	private ComboBox<String> statusSelector;
	private Button applyButton;
	private Button clearButton;
	
	public FilterView() {
		this.suburbSelector = new ComboBox<String>();
		this.typeSelector = new ComboBox<String>();
		this.bedroomsSelector = new ComboBox<Integer>();
		this.statusSelector = new ComboBox<String>();
		this.applyButton = new Button("Apply filter");
		this.clearButton = new Button("Clear filter");
		setSuburbs();
		setTypes();
		setBedrooms();
		setStatuses();
		setApply();
		setClear();
		setController();
	}
	
	private void setSuburbs() {
		suburbSelector.setPromptText("suburb");
		suburbSelector.setItems(FXCollections.observableArrayList("Carlton", "Brunswick"));
		this.getChildren().add(suburbSelector);
	}
	
	private void setTypes() {
		typeSelector.setPromptText("property type");
		typeSelector.setItems(FXCollections.observableArrayList("Apartment", "Premium Suite"));
		this.getChildren().add(typeSelector);
	}
	
	private void setBedrooms() {
		bedroomsSelector.setPromptText("bedrooms");
		bedroomsSelector.setItems(FXCollections.observableArrayList(1, 2, 3));
		this.getChildren().add(bedroomsSelector);
	}
	
	private void setStatuses() {
		statusSelector.setPromptText("status");
		statusSelector.setItems(FXCollections.observableArrayList("Available", "Rented", "Under Maintenance"));
		this.getChildren().add(statusSelector);
	}
	
	private void setApply() {
		this.getChildren().add(applyButton);
	}
	
	private void setClear() {
		this.getChildren().add(clearButton);
	}
	
	private void setController() {
		FilterController controller = new FilterController(this.suburbSelector, this.typeSelector, 
				this.bedroomsSelector, this.statusSelector, this.applyButton, this.clearButton);
		applyButton.setOnAction(controller);
		clearButton.setOnAction(controller);
	}

}
