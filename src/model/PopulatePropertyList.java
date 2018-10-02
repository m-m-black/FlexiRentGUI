package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.db.DatabaseMethods;
import view.HomeView;
import view.MainProgramWindow;
import view.PropertyListItem;

public class PopulatePropertyList {
	
	private HomeView view;
	private ArrayList<HashMap<String, String>> properties;
	private int rowNum;
	private ArrayList<String> currentProperties;
	
	public PopulatePropertyList() {
		this.view = MainProgramWindow.getHomeView();
		rowNum = 0;
		currentProperties = new ArrayList<String>();
	}
	
	public void populate() {
		fetchProperties();
		for (HashMap<String, String> property: properties) {
			// create new PropertyListItem using details from each RentalProperty, and add to grid
			if (!(currentProperties.contains(property.get("propertyID")))) {
				view.addItem(new PropertyListItem(property), 0, rowNum);
				currentProperties.add(property.get("propertyID"));
				rowNum++;
			}
		}
	}
	
	private void fetchProperties() {
		// get all RentalProperty rows from DB, extract the necessary data to pass to new PropertyListItem()
		properties = DatabaseMethods.getProperties();
	}

}
