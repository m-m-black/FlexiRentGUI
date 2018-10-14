package view;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HomeView extends BorderPane {
	
	static VBox list = new VBox();
	private static ArrayList<PropertyListItem> items;
	
	public HomeView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new FilterView());
		this.setCenter(list);
		items = new ArrayList<PropertyListItem>();
	}
	
	public void addItem(PropertyListItem item) {
		list.getChildren().add(item);
		items.add(item);
	}
	
	public static void updateList(ArrayList<PropertyListItem> filteredItems) {
		list.getChildren().clear();
		for (PropertyListItem item: filteredItems) {
			list.getChildren().add(item);
		}
	}
	
	public static ArrayList<PropertyListItem> getItems() {
		return items;
	}

}
