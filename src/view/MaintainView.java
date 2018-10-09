package view;

import controller.BackButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MaintainView extends BorderPane {
	
	public MaintainView() {
		setCenter(new Label("Maintain"));
		Button backButton = new Button("Back");
		backButton.setOnAction(new BackButtonHandler());
		setRight(backButton);
	}

}
