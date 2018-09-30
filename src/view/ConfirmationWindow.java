package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationWindow {
	
	public static void show(String message) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(300);
		stage.setMinHeight(200);
		Label label = new Label(message);
		Button exitButton = new Button("Okay");
		exitButton.setOnAction(e -> stage.close());
		VBox layout = new VBox();
		layout.getChildren().addAll(label, exitButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
	}

}
