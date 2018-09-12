package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RentPropertyView extends BorderPane {
	
	public RentPropertyView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new Label("Rent property"));
	}

}
