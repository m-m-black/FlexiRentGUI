package view;

import controller.BackButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CompleteView extends BorderPane {
	
	public CompleteView() {
		setCenter(new Label("Complete"));
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		setRight(backButton);
	}

}
