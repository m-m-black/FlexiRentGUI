package view;

import controller.listeners.BackButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RentView extends BorderPane {
	
	public RentView() {
		setCenter(new Label("Rent"));
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		setRight(backButton);
	}

}
