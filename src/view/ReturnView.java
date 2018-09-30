package view;

import controller.listeners.BackButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ReturnView extends BorderPane {
	
	public ReturnView() {
		setCenter(new Label("Return"));
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		setRight(backButton);
	}

}
