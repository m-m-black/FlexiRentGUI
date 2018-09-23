package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HomeView extends BorderPane {
	
	GridPane grid = new GridPane();
	
	public HomeView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new Label("Listing all properties"));
		this.makeGrid();
		this.setCenter(grid);
	}
	
	private void makeGrid() {
		grid.setHgap(10);
		grid.setVgap(10);
	}
	
	// add a PropertyListItem to the grid
	public void addItem(PropertyListItem item, int row, int col) {
		grid.add(item, row, col);
	}

}
