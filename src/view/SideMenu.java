package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class SideMenu extends BorderPane {
	
	private VBox topLabels;
	private VBox bottomLabels;
	private FlowPane[] labels;
	private Label exitLabel;
	
	public SideMenu(double width) {
		topLabels = new VBox();
		bottomLabels = new VBox();
		labels = new FlowPane[5];
		
		this.setTop(topLabels);
		this.setBottom(bottomLabels);
		
		setLabels();
		addLabels();
		
		exitLabel = new Label("Exit");
		bottomLabels.getChildren().add(exitLabel);
		
		topLabels.setMaxWidth(width);
		bottomLabels.setMaxWidth(width);
		bottomLabels.setPadding(new Insets(10, 10, 10, 10));
	}
	
	private void setLabels() {
		labels[0] = new FlowPane();
		labels[0].getChildren().add(new Label("Home"));
		labels[1] = new FlowPane();
		labels[1].getChildren().add(new Label("Add Property"));
		labels[2] = new FlowPane();
		labels[2].getChildren().add(new Label("Rent Property"));
		labels[3] = new FlowPane();
		labels[3].getChildren().add(new Label("Return Property"));
		labels[4] = new FlowPane();
		labels[4].getChildren().add(new Label("Property Maintenance"));
	}
	
	private void addLabels() {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setPadding(new Insets(30, 10, 30, 10));
			if (i % 2 == 0) {
				labels[i].setStyle("-fx-background-color: lightgreen");
			} else {
				labels[i].setStyle("-fx-background-color: lightblue");
			}
			topLabels.getChildren().add(labels[i]);
		}
	}

}
