package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PropertyMaintenanceView extends BorderPane {
	
	public PropertyMaintenanceView() {
		this.setStyle("-fx-background-color: white");
		this.setTop(new Label("Property maintenance"));
	}

}
