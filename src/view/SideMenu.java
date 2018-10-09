package view;

import controller.SideMenuClickHandler;
import controller.SideMenuHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class SideMenu extends BorderPane {
	
	private VBox topLabels;
	private VBox bottomLabels;
	private FlowPane[] labels;
	private FlowPane bottomLabel;
	private Label exitLabel;
	
	public SideMenu(double width) {
		topLabels = new VBox();
		bottomLabels = new VBox();
		labels = new FlowPane[2];
		bottomLabel = new FlowPane();
		
		this.setTop(topLabels);
		this.setBottom(bottomLabels);
		
		setLabels();
		addLabels();
		
		exitLabel = new Label("Exit");
		bottomLabel.getChildren().add(exitLabel);
		bottomLabel.setPadding(new Insets(30, 10, 30, 10));
		bottomLabel.setOnMouseClicked(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to exit?");
			alert.showAndWait()
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response -> System.exit(0));
		});
		bottomLabels.getChildren().add(bottomLabel);
		
		topLabels.setMaxWidth(width);
		bottomLabels.setMaxWidth(width);
	}
	
	private void setLabels() {
		labels[0] = new FlowPane();
		labels[0].getChildren().add(new Label("Home"));
		labels[0].getChildren().get(0).setId("Home");
		labels[1] = new FlowPane();
		labels[1].getChildren().add(new Label("Add Property"));
		labels[1].getChildren().get(0).setId("Add Property");
	}
	
	private void addLabels() {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setPadding(new Insets(30, 10, 30, 10));
			labels[i].setOnMouseEntered(new SideMenuHandler(labels[i], true));
			labels[i].setOnMouseExited(new SideMenuHandler(labels[i], false));
			labels[i].setOnMouseClicked(new SideMenuClickHandler(labels[i].getChildren().get(0).getId()));
			topLabels.getChildren().add(labels[i]);
			bottomLabel.setOnMouseEntered(new SideMenuHandler(bottomLabel, true));
			bottomLabel.setOnMouseExited(new SideMenuHandler(bottomLabel, false));
		}
	}
	
	public FlowPane[] getLabels() {
		return labels;
	}

}
