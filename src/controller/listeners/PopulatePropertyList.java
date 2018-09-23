package controller.listeners;

import java.util.ArrayList;

import model.RentalProperty;
import view.HomeView;

public class PopulatePropertyList {
	
	private HomeView view;
	private ArrayList<RentalProperty> properties;
	
	public PopulatePropertyList(HomeView view) {
		this.view = view;
	}
	
	public void populate() {
		for (RentalProperty property: properties) {
			// create new PropertyListItem using details from each RentalProperty, and add to grid
			// view.addItem(new PropertyListItem());
		}
	}
	
	private void fetchProperties() {
		// get all RentalProperty rows from DB, extract the necessary data to pass to new PropertyListItem()
	}

}
