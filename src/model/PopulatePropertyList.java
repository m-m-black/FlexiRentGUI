package model;

import java.util.ArrayList;
import java.util.HashMap;

import main.DatabaseMethods;
import view.HomeView;
import view.MainProgramWindow;
import view.PropertyListItem;

public class PopulatePropertyList {
	
	private HomeView view;
	private ArrayList<HashMap<String, String>> properties;
	private int rowNum;
	
	public PopulatePropertyList() {
		this.view = MainProgramWindow.getHomeView();
		rowNum = 0;
		fetchProperties();
	}
	
	public void populate() {
		for (HashMap<String, String> property: properties) {
			// create new PropertyListItem using details from each RentalProperty, and add to grid
			view.addItem(new PropertyListItem(property), 0, rowNum);
			rowNum++;
		}
	}
	
	private void fetchProperties() {
		// get all RentalProperty rows from DB, extract the necessary data to pass to new PropertyListItem()
		properties = DatabaseMethods.getProperties();
	}

}
