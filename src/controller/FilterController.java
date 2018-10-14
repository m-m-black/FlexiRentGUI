package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import view.HomeView;
import view.PropertyDetailWindow;
import view.PropertyListItem;

public class FilterController implements EventHandler<ActionEvent> {
	
	private ComboBox<String> suburbSelector;
	private ComboBox<String> typeSelector;
	private ComboBox<Integer> bedroomsSelector;
	private ComboBox<String> statusSelector;
	private Button applyButton;
	private Button clearButton;
	private ArrayList<PropertyListItem> items;
	private ArrayList<PropertyListItem> filteredItems;
	String suburbChoice;
	String typeChoice;
	int bedroomsChoice;
	String statusChoice;
	
	public FilterController(ComboBox<String> suburbSelector, ComboBox<String> typeSelector, 
			ComboBox<Integer> bedroomsSelector, ComboBox<String> statusSelector, Button applyButton, 
			Button clearButton) {
		this.suburbSelector = suburbSelector;
		this.typeSelector = typeSelector;
		this.bedroomsSelector = bedroomsSelector;
		this.statusSelector = statusSelector;
		this.applyButton = applyButton;
		this.clearButton = clearButton;
		this.filteredItems = new ArrayList<PropertyListItem>();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource().equals(applyButton)) {
			filteredItems.clear();
			setFilterCriteria();
			this.items = HomeView.getItems();
			for (PropertyListItem item: items) {
				PropertyDetailWindow window = item.getWindow();
				// currently only works if an option is selected for every ComboBox, none can be left empty
				if (window.getSuburb().compareTo(suburbChoice) == 0 && window.getType().compareTo(typeChoice) == 0 
						&& Integer.valueOf(window.getBedrooms()) == bedroomsChoice 
						&& window.getStatus().compareTo(statusChoice) == 0) {
					if (!filteredItems.contains(item)) {
						filteredItems.add(item);
					}
				}
			}
			HomeView.updateList(filteredItems);
			System.out.println(filteredItems.size());
		}
		if (e.getSource().equals(clearButton)) {
			filteredItems.clear();
			for (PropertyListItem item: items) {
				filteredItems.add(item);
			}
			HomeView.updateList(filteredItems);
		}
	}
	
	private void setFilterCriteria() {
		if (suburbSelector.getValue() == null) {
			suburbChoice = suburbSelector.getPromptText();
		} else {
			suburbChoice = suburbSelector.getValue();
		}
		if (typeSelector.getValue() == null) {
			typeChoice = typeSelector.getPromptText();
		} else {
			typeChoice = typeSelector.getValue();
		}
		if (bedroomsSelector.getValue() == null) {
			bedroomsChoice = 0;
		} else {
			bedroomsChoice = bedroomsSelector.getValue();
		}
		if (statusSelector.getValue() == null) {
			statusChoice = statusSelector.getPromptText();
		} else {
			statusChoice = statusSelector.getValue();
		}
	}

}
