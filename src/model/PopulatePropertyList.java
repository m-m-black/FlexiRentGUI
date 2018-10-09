package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.db.DatabaseMethods;
import view.HomeView;
import view.MainProgramWindow;
import view.PropertyListItem;

public class PopulatePropertyList {
	
	private HomeView view;
	private static ArrayList<HashMap<String, String>> properties;
	private int rowNum;
	private ArrayList<String> currentProperties;
	private HashMap<String, PropertyListItem> listItems;
	
	public PopulatePropertyList() {
		this.view = MainProgramWindow.getHomeView();
		rowNum = 0;
		currentProperties = new ArrayList<String>();
		listItems = new HashMap<String, PropertyListItem>();
	}
	
	public void populate() {
		fetchProperties();
		for (HashMap<String, String> property: properties) {
			// create new PropertyListItem using details from each RentalProperty, and add to grid
			if (!(currentProperties.contains(property.get("propertyID")))) {
				PropertyListItem item = new PropertyListItem(property);
				view.addItem(item, 0, rowNum);
				listItems.put(property.get("propertyID"), item);
				currentProperties.add(property.get("propertyID"));
				rowNum++;
			} else {
				listItems.get(property.get("propertyID")).update(property);
			}
		}
	}
	
	private void fetchProperties() {
		// get all RentalProperty rows from DB, extract the necessary data to pass to new PropertyListItem()
		properties = DatabaseMethods.getProperties();
	}
	
	public static ArrayList<HashMap<String, String>> getProperties() {
		return properties;
	}

}
