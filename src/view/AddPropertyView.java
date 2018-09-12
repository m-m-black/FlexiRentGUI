package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class AddPropertyView extends BorderPane {
	
	public AddPropertyView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new Label("Add Property"));
	}

}
