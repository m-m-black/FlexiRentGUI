package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomeView extends BorderPane {
	
	public HomeView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new Label("Listing all properties"));
	}

}
